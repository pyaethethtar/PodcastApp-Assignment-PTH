package com.example.podcastapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.podcastapp.mvp.views.DetailsView

interface DetailsPresenter : BasePresenter<DetailsView> {

    fun onUiReady(lifecycleOwner: LifecycleOwner, id: String)
}