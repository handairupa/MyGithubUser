package com.example.mygithubuser.find

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubuser.R

class AdapterPencarian(private val listReview: ArrayList<PencarianAkun>) : RecyclerView.Adapter<AdapterPencarian.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_row_user_pencarian, viewGroup, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val (name,photo)=listReview[position]

        viewHolder.tvtextusername.text = name
        Glide.with(viewHolder.itemView.context)
        .load(photo)
        .into(viewHolder.imgItem)

        viewHolder.itemView.setOnClickListener {onItemClickCallback.onItemClicked(listReview[viewHolder.adapterPosition])}

    }
    override fun getItemCount(): Int {
        return listReview.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgItem : ImageView = view.findViewById(R.id.img_item_photo)
        val tvtextusername : TextView = view.findViewById(R.id.tv_item_username)


    }

    private lateinit var onItemClickCallback: AdapterPencarian.OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: PencarianAkun)
    }
}