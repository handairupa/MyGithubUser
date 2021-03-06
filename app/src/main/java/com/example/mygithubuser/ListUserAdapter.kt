package com.example.mygithubuser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListUserAdapter(private val listUser: ArrayList<User>) : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: Any){
        this.onItemClickCallback = onItemClickCallback as OnItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvUsername: TextView = itemView.findViewById(R.id.tv_item_username)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, username, avatar) = listUser[position]

        Glide.with(holder.itemView.context)
            .load(avatar)
            .into(holder.imgPhoto)

        holder.tvName.text = name
        holder.tvUsername.text = username
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])}
    }

    override fun getItemCount(): Int = listUser.size

}