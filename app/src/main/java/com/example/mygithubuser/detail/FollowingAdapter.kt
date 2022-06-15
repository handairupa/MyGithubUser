package com.example.mygithubuser.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubuser.R

class FollowingAdapter(private val listdata: List<ResponseFollowingItem>): RecyclerView.Adapter<FollowingAdapter.ViewHolder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup,  viewType: Int) : ViewHolder{
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_row_following, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(ViewHolder: ViewHolder, position: Int) {
        val data = listdata.get(position)

        ViewHolder.tvtextusername.text = data.login
        Glide.with(ViewHolder.itemView.context)
            .load(data.avatarUrl)
            .into(ViewHolder.imgItem)
    }

    override fun getItemCount(): Int {
        return listdata.size

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgItem : ImageView = view.findViewById(R.id.img_usr)
        val tvtextusername : TextView = view.findViewById(R.id.Username_)
    }
}
