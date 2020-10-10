package com.example.podcastapp.mvp.views

import com.example.podcastapp.data.vos.GenresVO
import com.example.shared.BaseView

interface SearchView : BaseView {
    fun displayCategories(categories: List<GenresVO>)

}