package com.example.podcastapp.data.models

import android.content.Context
import com.example.podcastapp.network.PodcastAPI
import com.example.podcastapp.persistence.db.PodcastDB
import com.example.podcastapp.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel {

    //protected var mPodcastApi : PodcastAPI
    protected lateinit var mTheDB : PodcastDB
//    var baseUrl = BASE_URL

//    init {
//        val mOkHttpClient= OkHttpClient.Builder()
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .readTimeout(15, TimeUnit.SECONDS)
//            .writeTimeout(15, TimeUnit.SECONDS)
//            .build()
//
//        val retrofit= Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .client(mOkHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//
//        mPodcastApi = retrofit.create(PodcastAPI::class.java)
//    }

    fun initDatabase(context: Context) {
        mTheDB = PodcastDB.getDBInstance(context)
    }

}