package com.example.podcastapp.delegates

import com.example.podcastapp.data.vos.PodcastVO

interface PodcastDelegate {

    fun onTapPodcastItem(id : String)
    fun onTapDownload(podcastVO: PodcastVO)
}