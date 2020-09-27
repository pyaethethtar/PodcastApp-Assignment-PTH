package com.example.podcastapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.podcastapp.mvp.views.SearchView

interface SearchPresenter : BasePresenter<SearchView> {

    fun onUiReady(lifecycleOwner: LifecycleOwner)
}