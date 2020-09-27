package com.example.podcastapp.mvp.views

import com.example.podcastapp.data.vos.PodcastVO

interface DownloadView : BaseView {

    fun displayEmptyView()
    fun displayDownloadedShows(podcasts : List<PodcastVO>)
    fun navigateToUpNext()
    fun navigateToDetails(id : String)
}