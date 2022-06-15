package com.example.mygithubuser.love

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubuser.databinding.ListItemLoveBinding

class AdapterLove : RecyclerView.Adapter<AdapterLove.LoveViewHolder> (){

    private val listdata = ArrayList<DataUserLove>()
    private var onItemClickCallback: OnItemClickCallback? = null


    inner class LoveViewHolder (private val binding : ListItemLoveBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindlove(data : DataUserLove){
                binding.root.setOnClickListener{
                    onItemClickCallback?.onItemClicked(data)
                }
            binding.apply {
                Glide.with(itemView)
                    .load(data.avatar_userlove)
                    .circleCrop()
                    .into(imgUsr)

                tvName.text = data.name_userlove
                tvUsername.text = data.username_userlove
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterLove.LoveViewHolder {
        val viewLove = ListItemLoveBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LoveViewHolder((viewLove))
    }

    override fun onBindViewHolder(holder: AdapterLove.LoveViewHolder, position: Int) {

        holder.bindlove(listdata[position])

    }

    override fun getItemCount(): Int = listdata.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataUserLove)
    }

    fun setList(love : ArrayList<DataUserLove>){
        listdata.clear()
        listdata.addAll(love)
        notifyDataSetChanged()
    }

}