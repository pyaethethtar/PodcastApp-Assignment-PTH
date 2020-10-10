package com.example.shared

import androidx.lifecycle.ViewModel

abstract class AbstractBasePresenter<T : BaseView> : BasePresenter<T>, ViewModel() {

    var mView: T ?= null
    override fun initPresenter(view: T) {
        mView = view
    }
}