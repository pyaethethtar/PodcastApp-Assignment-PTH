package com.example.podcastapp.mvp.views

import com.example.podcastapp.data.vos.PodcastVO

interface DetailsView : BaseView {

    fun displayPodcastDetails(podcast: PodcastVO)
}