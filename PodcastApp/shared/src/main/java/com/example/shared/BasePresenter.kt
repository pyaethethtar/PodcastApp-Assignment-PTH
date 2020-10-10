package com.example.shared

interface BasePresenter<T : BaseView> {

    fun initPresenter(view : T)
}