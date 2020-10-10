package com.example.podcastapp.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.podcastapp.R
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.mvp.presenters.DetailsPresenter
import com.example.podcastapp.mvp.presenters.impls.DetailsPresenterImpl
import com.example.podcastapp.mvp.views.DetailsView
import com.example.shared.BaseActivity
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity() : BaseActivity(), DetailsView {

    lateinit var mPresenter : DetailsPresenter
    private var podcastId : String = "4d82e50314174754a3b603912448e812"
    private lateinit var player : SimpleExoPlayer
    private var playWhenReady : Boolean= false
    private var currentWindow : Int = 0
    private var playbackPosition: Long = 0


    companion object{
        const val PODCAST_ID_EXTRA = "PODCAST_ID_EXTRA"
        fun newIntent(context: Context, id : String) : Intent{
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(PODCAST_ID_EXTRA, id)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        podcastId = intent.getStringExtra(PODCAST_ID_EXTRA)
        setUpPresenter()
        mPresenter.onUiReady(this, podcastId)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProviders.of(this).get(DetailsPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }


    override fun displayPodcastDetails(podcast: PodcastVO) {
        Glide.with(this).load(podcast.image).into(ivToolbar)
        tvDetailsTitle.text = podcast.title
        tvDetailsDescription.text = podcast.description
        initializePlayer(podcast.audio)
    }


    override fun onStart() {
        super.onStart()
        //initializePlayer()
    }

    override fun onStop() {
        releasePlayer()
        super.onStop()
    }

    fun initializePlayer(audio : String){
        player = ExoPlayerFactory.newSimpleInstance(this)
        playerViewSmall.player = player

        var uri = Uri.parse(audio)
        var dataSourceFactory : DataSource.Factory = DefaultDataSourceFactory(this, "exoplayer-codelab")
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