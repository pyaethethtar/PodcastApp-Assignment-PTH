package com.example.podcastapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.podcastapp.data.models.PodcastModel
import com.example.podcastapp.data.models.PodcastModelImpl
import com.example.podcastapp.mvp.presenters.DetailsPresenter
import com.example.podcastapp.mvp.views.DetailsView

class DetailsPresenterImpl: DetailsPresenter, AbstractBasePresenter<DetailsView>() {

    var mPodcastModel : PodcastModel = PodcastModelImpl

    override fun onUiReady(lifecycleOwner: LifecycleOwner, id : String) {

        requestPodcastDetails(lifecycleOwner, id)
    }

    private fun requestPodcastDetails(lifecycleOwner: LifecycleOwner, id: String) {
        mPodcastModel.getPodcastItemById(id)
            .observe(lifecycleOwner, Observer {
                if (it!=null){
                    mView?.displayPodcastDetails(it.data)
                }
            })
    }
}