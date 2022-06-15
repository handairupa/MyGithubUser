package com.example.mygithubuser.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.mygithubuser.koneksi.KoneksiConfig
import com.example.mygithubuser.R
import com.example.mygithubuser.databinding.ActivityDetailUserBinding
import com.example.mygithubuser.love.ModelLove
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    private var love_user: Int = 0
    lateinit var model : ModelLove


    @StringRes
    private val TAB_TITLES = intArrayOf(
        R.string.follower,
        R.string.following
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        getDataUser(intent.getStringExtra(UsernameUser).toString())
        model  = ViewModelProvider(this).get(ModelLove::class.java)

    }

    private fun getDataUser(username: String) {
        showloading(true)
        val client = KoneksiConfig.getApiService().getdetailUser(username)
        client.enqueue(object : Callback<ResponseDetail>{
            override fun onResponse(
                call: Call<ResponseDetail>,
                response: Response<ResponseDetail>
            ) {
                if (response.isSuccessful){
                    val data = response.body()
                    if (data!=null){
                        showloading(false)
                        binding.tvName.text =data.name
                        binding.tvCompany.text = data.company
                        binding.tvLocation.text = data.location
                        binding.tvUsername.text = data.login

                        Glide.with(this@DetailUserActivity)
                            .load(data.avatarUrl)
                            .into(binding.ivAvatar)



                        CoroutineScope(Dispatchers.IO).launch {
                             val lovedata= data.id
                             val check = model.checkLove(lovedata)

                            withContext(Dispatchers.Main){
                                if (check == 1){
                                    binding.fabFavorite.setImageDrawable(
                                        ContextCompat.getDrawable(
                                            applicationContext,
                                            R.drawable.ic_baseline_favorite_border_24
                                        )
                                    )
                                    model.AddLove(data.id, data.login, data.name, data.avatarUrl)
                                    love_user = 1
                                }
                            }
                        }

                        binding.fabFavorite.setOnClickListener{
                            if (love_user.equals(1)){
                                binding.fabFavorite.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        applicationContext,
                                        R.drawable.breakheart
                                    )
                                )
                            model.DeleteLove(data.id)
                                Toast.makeText(applicationContext, "Dislike${data.login}", Toast.LENGTH_SHORT).show()
                                love_user = 0
                            }else{
                                binding.fabFavorite.setImageDrawable(
                                    ContextCompat.getDrawable(
                                        applicationContext,
                                        R.drawable.ic_baseline_favorite_border_24
                                    )
                                )
                                model.AddLove(data.id, data.login, data.name, data.avatarUrl)
                                Toast.makeText(applicationContext, "Love Sukses", Toast.LENGTH_SHORT).show()

                                love_user =1

                            }
                        }
                    }
                }else{
                    Toast.makeText(applicationContext, response.message(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ResponseDetail>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                showloading(false)

            }

        })
    }

    private fun showloading(loading : Boolean){
            if (loading){
                binding.loading.visibility  = View.VISIBLE
            }else{
                binding.loading.visibility = View.GONE
            }
    }

    companion object{
        const val UsernameUser = "isi"
    }
}
