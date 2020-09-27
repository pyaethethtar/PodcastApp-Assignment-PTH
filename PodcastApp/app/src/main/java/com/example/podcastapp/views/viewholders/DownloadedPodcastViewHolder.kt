package com.example.podcastapp.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.delegates.DownloadDelegate
import kotlinx.android.synthetic.main.item_podcast.view.*
import kotlinx.android.synthetic.main.item_podcast.view.ivPodcast
import kotlinx.android.synthetic.main.item_podcast.view.tvPodcastTitle
import kotlinx.android.synthetic.main.item_podcast_downloaded.view.*

class DownloadedPodcastViewHolder(itemView: View, private val delegate: DownloadDelegate) : BaseViewHolder<PodcastVO>(itemView) {

    var podcastId : String = ""

    init {
        itemView.setOnClickListener {
            delegate.onTapPodcastItem(podcastId)
        }

    }

    override fun bindData(data: PodcastVO) {
        podcastId = data.id
        Glide.with(itemView.context).load(data.image).into(itemView.ivPodcast)
        itemView.tvPodcastTitle.text = data.title
        itemView.tvPodcastDescription.text = data.description
    }
}