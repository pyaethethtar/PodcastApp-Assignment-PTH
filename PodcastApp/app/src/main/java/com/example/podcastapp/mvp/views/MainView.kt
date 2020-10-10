package com.example.podcastapp.mvp.views

import com.example.podcastapp.data.vos.PodcastVO
import com.example.shared.BaseView

interface MainView : BaseView {

    fun displayPlayer(podcast: PodcastVO)
    fun displayUpNextList(podcasts: List<PodcastVO>)
    fun navigateToDetails(id: String)
    fun downloadPodcast(podcast: PodcastVO)
}