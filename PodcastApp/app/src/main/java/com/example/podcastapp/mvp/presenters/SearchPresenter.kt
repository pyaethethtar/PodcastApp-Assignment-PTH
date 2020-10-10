package com.example.podcastapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.podcastapp.mvp.views.SearchView
import com.example.shared.BasePresenter

interface SearchPresenter : BasePresenter<SearchView> {

    fun onUiReady(lifecycleOwner: LifecycleOwner)
}