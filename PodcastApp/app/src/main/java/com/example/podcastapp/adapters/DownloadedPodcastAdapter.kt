package com.example.podcastapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.podcastapp.R
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.delegates.DownloadDelegate
import com.example.shared.BaseViewHolder
import com.example.podcastapp.views.viewholders.DownloadedPodcastViewHolder
import com.example.shared.BaseAdapter

class DownloadedPodcastAdapter(private val delegate: DownloadDelegate): BaseAdapter<BaseViewHolder<PodcastVO>, PodcastVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<PodcastVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_podcast_downloaded, parent, false)
        return DownloadedPodcastViewHolder(view, delegate)
    }
}