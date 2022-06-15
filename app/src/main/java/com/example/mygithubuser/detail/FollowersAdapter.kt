    package com.example.mygithubuser.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubuser.R

    class FollowersAdapter(private val lisdata: List<ResponseFollowersItem>) : RecyclerView.Adapter<FollowersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_row_followers, viewGroup, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data = lisdata.get(position)

        viewHolder.tvtextusername.text = data.login
        Glide.with(viewHolder.itemView.context)
        .load(data.avatarUrl)
        .into(viewHolder.imgItem)
    }
    override fun getItemCount(): Int {
        return lisdata.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgItem : ImageView = view.findViewById(R.id.img_usr)
        val tvtextusername : TextView = view.findViewById(R.id.Username_)
    }
}