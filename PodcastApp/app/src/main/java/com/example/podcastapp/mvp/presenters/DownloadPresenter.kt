package com.example.podcastapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.podcastapp.delegates.DownloadDelegate
import com.example.podcastapp.mvp.views.DownloadView
import com.example.shared.BasePresenter

interface DownloadPresenter : BasePresenter<DownloadView>, DownloadDelegate{

    fun onUiReady(lifecycleOwner: LifecycleOwner)
    fun onTapFindNew()
    fun onTapReload()
}