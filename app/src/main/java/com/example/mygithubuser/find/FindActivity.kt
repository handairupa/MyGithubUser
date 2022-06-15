package com.example.mygithubuser.find

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuser.detail.DetailUserActivity
import com.example.mygithubuser.koneksi.KoneksiConfig
import com.example.mygithubuser.databinding.ActivityFindBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindActivity : AppCompatActivity() {
    companion object{
        const val kuncicari = "kunci"
        private val TAG = FindActivity::class.java.simpleName
    }
    private lateinit var binding: ActivityFindBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.listfinduser.layoutManager = layoutManager

        GetData()
    }
    private fun showLoading(isLoading : Boolean){
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun GetData() {
        showLoading(true)
        val client = KoneksiConfig.getApiService().getpencarian(intent.getStringExtra(kuncicari).toString())
       client.enqueue(object : Callback<ResponsePencarian> {
           override fun onResponse(
               call: Call<ResponsePencarian>,
               response: Response<ResponsePencarian>
           ) {
               if (response.isSuccessful) {
                   val hasil = response.body()
                   if (hasil != null) {
                       showLoading(false)

                       setdata(hasil.items)
                   }
               } else {
                   Log.e(TAG, "onFailure: ${response.message()}")
               }
           }

           override fun onFailure(call: Call<ResponsePencarian>, t: Throwable) {
               TODO("Not yet implemented")
           }

       })
    }

    private fun setdata(items: List<ItemsItem>) {
        val listReview = ArrayList<PencarianAkun>()

        for (review in items) {
            val user = PencarianAkun(review.login, review.avatarUrl)
            listReview.add(user)
        }

        val adapter = AdapterPencarian(listReview)
        binding.listfinduser.adapter = adapter

        adapter.setOnItemClickCallback(object : AdapterPencarian.OnItemClickCallback {
            override fun onItemClicked(data: PencarianAkun) {
                Toast.makeText(applicationContext, data.name, Toast.LENGTH_SHORT).show()
                val pindah = Intent(this@FindActivity, DetailUserActivity::class.java)
                pindah.putExtra(DetailUserActivity.UsernameUser,data.name)
                startActivity(pindah)
            }
        })
    }
}