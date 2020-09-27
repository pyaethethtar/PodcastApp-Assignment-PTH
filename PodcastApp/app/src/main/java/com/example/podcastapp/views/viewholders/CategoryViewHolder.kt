package com.example.podcastapp.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.podcastapp.data.vos.GenresVO
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryViewHolder(itemView: View) : BaseViewHolder<GenresVO>(itemView) {


    override fun bindData(data: GenresVO) {
        Glide.with(itemView.context).load("https://cdn-images-1.listennotes.com/podcasts/exponent-ben-thompson-james-allworth-OaJSjb4xQv3.1400x1400.jpg").into(itemView.ivCategory)
        itemView.tvCategoryName.text = data.name
    }
}