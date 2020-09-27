package com.example.podcastapp.views.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.delegates.PodcastDelegate
import kotlinx.android.synthetic.main.activity_details.view.*
import kotlinx.android.synthetic.main.item_podcast.view.*
import kotlinx.android.synthetic.main.viewpod_player.view.*

class PodcastViewHolder(itemView: View, var delegate: PodcastDelegate) : BaseViewHolder<PodcastVO>(itemView) {

    var podcastId : String = ""
    var hour : Int = 0
    var min : Int = 0

    init {
        itemView.setOnClickListener {
            delegate.onTapPodcastItem(podcastId)
        }

    }

    override fun bindData(data: PodcastVO) {
        podcastId = data.id
        Glide.with(itemView.context).load(data.image).into(itemView.ivPodcast)
        itemView.tvPodcastTitle.text = data.title

        hour = data.audio_length.div(60)
        min = data.audio_length.rem(60)
        if(hour<1){
            itemView.tvPodcastTime.text = min.toString()+" min"
        }
        else{
            itemView.tvPodcastTime.text = hour.toString()+"h "+min.toString()+"min left"
        }


        itemView.btnDownload.setOnClickListener {
            delegate.onTapDownload(data)
        }
    }


}