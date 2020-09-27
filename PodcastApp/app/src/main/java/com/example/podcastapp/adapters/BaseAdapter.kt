package com.example.podcastapp.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.podcastapp.views.viewholders.BaseViewHolder

abstract class BaseAdapter<T: BaseViewHolder<W>, W> : RecyclerView.Adapter<T>() {

    var mData : MutableList<W> = mutableListOf()

    override fun getItemCount(): Int {
        return mData.count()
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.bindData(mData[position])
    }

    fun setNewData(data: List<W>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun appendData(data: W){
        mData.add(data)
        notifyDataSetChanged()
    }
}