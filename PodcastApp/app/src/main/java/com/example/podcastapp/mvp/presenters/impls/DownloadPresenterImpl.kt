package com.example.podcastapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.podcastapp.data.models.PodcastModel
import com.example.podcastapp.data.models.PodcastModelImpl
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.mvp.presenters.DownloadPresenter
import com.example.podcastapp.mvp.views.DownloadView
import com.example.shared.AbstractBasePresenter

class DownloadPresenterImpl : AbstractBasePresenter<DownloadView>(), DownloadPresenter{

    var mPodcastModel : PodcastModel = PodcastModelImpl
    lateinit var mLifecycleOwner: LifecycleOwner

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
        mLifecycleOwner = lifecycleOwner
        requestDownloadedPodcasts()
    }

    override fun onTapFindNew() {
        mView?.navigateToUpNext()
    }

    override fun onTapReload() {
        requestDownloadedPodcasts()
    }

    override fun onTapPodcastItem(id : String) {
        mView?.navigateToDetails(id)
    }

    private fun requestDownloadedPodcasts(){
        mPodcastModel.getDownloadedData()
            .observe(mLifecycleOwner, Observer {
                if (it.isNotEmpty()){
                    var downloads : MutableList<PodcastVO> = mutableListOf()
                    for (item in it){
                        downloads.add(item.downloadVo)
                    }
                    mView?.displayDownloadedShows(downloads)
                }
                else{
                    mView?.displayEmptyView()
                }
            })
    }


}