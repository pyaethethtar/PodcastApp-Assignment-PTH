package com.example.podcastapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.podcastapp.mvp.views.DetailsView
import com.example.shared.BasePresenter

interface DetailsPresenter : BasePresenter<DetailsView> {

    fun onUiReady(lifecycleOwner: LifecycleOwner, id: String)
}