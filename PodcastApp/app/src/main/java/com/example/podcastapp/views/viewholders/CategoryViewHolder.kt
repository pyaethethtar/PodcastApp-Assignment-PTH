package com.example.podcastapp.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.podcastapp.R
import com.example.podcastapp.data.vos.GenresVO
import com.example.shared.BaseViewHolder
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryViewHolder(itemView: View) : BaseViewHolder<GenresVO>(itemView) {


    override fun bindData(data: GenresVO) {
        Glide.with(itemView.context).load(itemView.context.getString(R.string.img_podcast)).into(itemView.ivCategory)
        itemView.tvCategoryName.text = data.name
    }
}