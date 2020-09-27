package com.example.podcastapp.mvp.presenters.impls

import androidx.lifecycle.ViewModel
import com.example.podcastapp.mvp.presenters.BasePresenter
import com.example.podcastapp.mvp.views.BaseView

abstract class AbstractBasePresenter<T : BaseView> : BasePresenter<T>, ViewModel() {

    var mView: T ?= null
    override fun initPresenter(view: T) {
        mView = view
    }
}