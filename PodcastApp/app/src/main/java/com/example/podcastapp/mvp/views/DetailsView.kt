package com.example.podcastapp.mvp.views

import com.example.podcastapp.data.vos.PodcastVO
import com.example.shared.BaseView

interface DetailsView : BaseView {

    fun displayPodcastDetails(podcast: PodcastVO)
}