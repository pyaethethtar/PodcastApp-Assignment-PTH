package com.example.podcastapp.mvp.views

import com.example.podcastapp.data.vos.GenresVO

interface SearchView : BaseView {
    fun displayCategories(categories: List<GenresVO>)

}