package com.example.podcastapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.delegates.PodcastDelegate
import com.example.podcastapp.mvp.views.MainView
import com.example.shared.BasePresenter

interface MainPresenter : BasePresenter<MainView>, PodcastDelegate {

    fun onUiReady(lifecycleOwner: LifecycleOwner)
    fun saveDownloadedPodcast(podcast : PodcastVO)
}