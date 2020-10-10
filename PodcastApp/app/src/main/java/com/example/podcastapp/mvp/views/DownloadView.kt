package com.example.podcastapp.mvp.views

import com.example.podcastapp.data.vos.PodcastVO
import com.example.shared.BaseView

interface DownloadView : BaseView {

    fun displayEmptyView()
    fun displayDownloadedShows(podcasts : List<PodcastVO>)
    fun navigateToUpNext()
    fun navigateToDetails(id : String)
}