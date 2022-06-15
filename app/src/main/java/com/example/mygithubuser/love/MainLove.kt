package com.example.mygithubuser.love

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuser.R
import com.example.mygithubuser.databinding.ActivityDetailUserBinding
import com.example.mygithubuser.databinding.ActivityMainBinding
import com.example.mygithubuser.databinding.ActivityMainLoveBinding
import com.example.mygithubuser.detail.DetailUserActivity

class MainLove : AppCompatActivity() {

    private lateinit var binding: ActivityMainLoveBinding
    private lateinit var adapter : AdapterLove
    private lateinit var viewModel : ModelLove


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainLoveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AdapterLove()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(this).get(ModelLove::class.java)
        adapter.setOnItemClickCallback(object : AdapterLove.OnItemClickCallback{
            override fun onItemClicked(data: DataUserLove) {
                Intent(this@MainLove, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.UsernameUser, data.username_userlove)
                    startActivity(it)
                }
            }

        })

        binding.apply {
        binding.rvlove.setHasFixedSize(true)
        binding.rvlove.layoutManager = LinearLayoutManager(this@MainLove)
        binding.rvlove.adapter = adapter
        }

        viewModel.getLove()?.observe(this,{
            if (it!= null){
                val listdata = viewListData(it)
                adapter.setList(listdata)
            }

        })

    }

    private fun viewListData(users: List<DataUserLove>): ArrayList<DataUserLove> {
        val listUsers = java.util.ArrayList<DataUserLove>()
        for (user in users) {
            val viewListUser = DataUserLove(
                user.id_userlove,
                user.username_userlove,
                user.name_userlove,
                user.avatar_userlove
            )
            listUsers.add(viewListUser)
        }
        return listUsers
    }
}