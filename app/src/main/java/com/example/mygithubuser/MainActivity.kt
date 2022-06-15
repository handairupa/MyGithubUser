package com.example.mygithubuser

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuser.detail.DetailUserActivity
import com.example.mygithubuser.find.FindActivity
import com.example.mygithubuser.databinding.ActivityMainBinding
import com.example.mygithubuser.love.MainLove
import com.example.mygithubuser.settings.MenuSettings

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

        binding.rvUser.setHasFixedSize(true)
        list.addAll(listUser)

        showRecyclerList()

        binding.svUserr.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(kunci: String?): Boolean {
                val cari = Intent(this@MainActivity, FindActivity::class.java)
                cari.putExtra(FindActivity.kuncicari, kunci)
                startActivity(cari)

                return false

            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu1 -> {
                val i = Intent(this, MenuSettings::class.java)
                startActivity(i)
                true
            }
            R.id.menu2 -> {
                val i = Intent(this, MainLove::class.java)
                startActivity(i)
                true
            }
            else -> true
        }
    }

    private val listUser: ArrayList<User>
        @SuppressLint("Recycle")
        get() {
            val dataName = resources.getStringArray(R.array.name)
            val dataAvatar = resources.obtainTypedArray(R.array.avatar)
            val dataUsername = resources.getStringArray(R.array.username)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataCompany = resources.getStringArray(R.array.company)
            val listUser = ArrayList<User>()

            for (i in dataName.indices) {
                val user = User(
                    dataName[i],
                    dataUsername[i],
                    dataAvatar.getResourceId(i, -1),
                    dataLocation[i],
                    dataCompany[i]
                )
                listUser.add(user)
            }
            return listUser
        }

    private fun showRecyclerList() {

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(list)
        binding.rvUser.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {

                val move = Intent(this@MainActivity, DetailUserActivity::class.java)
                move.putExtra(DetailUserActivity.UsernameUser, data.username)
                startActivity(move)

            }
        })
    }

}
