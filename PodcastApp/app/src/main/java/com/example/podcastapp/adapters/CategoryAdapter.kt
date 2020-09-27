package com.example.podcastapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.podcastapp.R
import com.example.podcastapp.data.vos.GenresVO
import com.example.podcastapp.views.viewholders.BaseViewHolder
import com.example.podcastapp.views.viewholders.CategoryViewHolder
import com.example.podcastapp.views.viewholders.PodcastViewHolder

class CategoryAdapter : BaseAdapter<BaseViewHolder<GenresVO>, GenresVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<GenresVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }
}