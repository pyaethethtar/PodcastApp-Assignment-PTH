package com.example.podcastapp.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.podcastapp.data.models.PodcastModel
import com.example.podcastapp.data.models.PodcastModelImpl
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.mvp.presenters.MainPresenter
import com.example.podcastapp.mvp.views.MainView
import com.example.shared.AbstractBasePresenter

class MainPresenterImpl : AbstractBasePresenter<MainView>(),
    MainPresenter {

    var mPodcastModel : PodcastModel = PodcastModelImpl

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {

        //loadPodcastsFromApiAndSaveToDatabase()
        requestRandomPodcast(lifecycleOwner)
        requestUpNextPodcasts(lifecycleOwner)
    }


    override fun onTapPodcastItem(id: String) {
        mView?.navigateToDetails(id)
    }

    override fun onTapDownload(podcast: PodcastVO) {
        mView?.downloadPodcast(podcast )
    }

    override fun saveDownloadedPodcast(podcast: PodcastVO) {
        mPodcastModel.saveDownloadedData(podcast)
    }


    private fun requestUpNextPodcasts(lifecycleOwner: LifecycleOwner) {
        mPodcastModel.getUpNextPodcastList(
            onError = {Log.e("error", "Podcast Error")}
        ).observe(lifecycleOwner, Observer {
            if (it.isNotEmpty()){
                var podcasts : MutableList<PodcastVO> = mutableListOf()
                for (item in it){
                    podcasts.add(item.data)
                }
                mView?.displayUpNextList(podcasts)
            }


        })
    }

    private fun requestRandomPodcast(lifecycleOwner: LifecycleOwner) {
        mPodcastModel.getRandomPodcast(
            onError = {Log.e("error", "Podcast Error")}
        ).observe(lifecycleOwner, Observer {
            if (it!=null){
                mView?.displayPlayer(it.data)
            }
        })
    }

    private fun loadPodcastsFromApiAndSaveToDatabase() {
        mPodcastModel.getPodcastsFromApiAndSaveToDatabase(onSuccess = {}, onError = {})

    }
}