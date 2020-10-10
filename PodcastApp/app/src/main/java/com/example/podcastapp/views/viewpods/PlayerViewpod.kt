package com.example.podcastapp.views.viewpods

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.podcastapp.R
import com.example.podcastapp.data.vos.PodcastVO
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.custom_player.view.*
import kotlinx.android.synthetic.main.viewpod_player.*
import kotlinx.android.synthetic.main.viewpod_player.view.*
import org.w3c.dom.Text

class PlayerViewpod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private lateinit var tvPlayerTime : TextView
    private lateinit var player : SimpleExoPlayer
    private var playWhenReady : Boolean= false
    private var currentWindow : Int = 0
    private var playbackPosition: Long = 0

    override fun onFinishInflate() {
        super.onFinishInflate()

        tvPlayerTime = findViewById<TextView>(R.id.exo_position)
    }

    fun displayPlayer(podcast : PodcastVO){

        tvDescription.text = podcast.description
        Glide.with(this).load(podcast.image).into(ivPlayerImage)
        tvPlayerTitle.text = podcast.title
        tvPlayerDescription.text = podcast.description

        initializePlayer(podcast.audio)
    }

    fun initializePlayer(audio : String){
        player = ExoPlayerFactory.newSimpleInstance(context)
        playerView.player = player

        var uri = Uri.parse(audio)
        var dataSourceFactory : DataSource.Factory = DefaultDataSourceFactory(context, "exoplayer-codelab")
        var mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)

        player.playWhenReady = playWhenReady
        player.seekTo(currentWindow, playbackPosition)
        player.prepare(mediaSource, false, false)
    }

    fun releasePlayer() {
        playWhenReady = player.playWhenReady
        playbackPosition = player.currentPosition
        currentWindow = player.currentWindowIndex
        player.release()
    }










}