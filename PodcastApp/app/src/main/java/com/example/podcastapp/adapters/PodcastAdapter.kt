package com.example.podcastapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.podcastapp.R
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.delegates.PodcastDelegate
import com.example.podcastapp.views.viewholders.BaseViewHolder
import com.example.podcastapp.views.viewholders.PodcastViewHolder

class PodcastAdapter(var delegate: PodcastDelegate) : BaseAdapter<BaseViewHolder<PodcastVO>, PodcastVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<PodcastVO> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_podcast, parent, false)
        return PodcastViewHolder(view, delegate)
    }
}