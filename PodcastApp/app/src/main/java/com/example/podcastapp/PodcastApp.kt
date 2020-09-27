package com.example.podcastapp

import android.app.Application
import com.example.podcastapp.data.models.PodcastModelImpl

class PodcastApp: Application() {

    override fun onCreate() {
        super.onCreate()
        PodcastModelImpl.initDatabase(applicationContext)
    }
}