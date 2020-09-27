package com.example.podcastapp.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.podcastapp.data.models.PodcastModel
import com.example.podcastapp.data.models.PodcastModelImpl
import com.example.podcastapp.mvp.presenters.SearchPresenter
import com.example.podcastapp.mvp.views.SearchView

class SearchPresenterImpl : SearchPresenter, AbstractBasePresenter<SearchView>() {

    var mPodcastModel : PodcastModel = PodcastModelImpl

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {

        requestCategories(lifecycleOwner)
    }

    private fun requestCategories(lifecycleOwner: LifecycleOwner) {
        mPodcastModel.getAllCategories(
            onError = { Log.e("error", "Podcast Error")}
        ).observe(lifecycleOwner, Observer {
            if (it.isNotEmpty()){
                mView?.displayCategories(it)
            }
            else{
                Log.e("PodcastResult", "Empty")
            }
        })
    }

}