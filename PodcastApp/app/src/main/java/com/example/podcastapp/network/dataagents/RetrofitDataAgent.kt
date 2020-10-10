package com.example.podcastapp.network.dataagents

import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.network.PodcastAPI
import com.example.podcastapp.network.responses.GetCategoriesResponse
import com.example.podcastapp.network.responses.GetPodcastListResponse
import com.example.podcastapp.utils.BASE_URL
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgent : PodcastAPI {

    var mPodcastApi : PodcastAPI
    var baseUrl = BASE_URL

    init {
        val mOkHttpClient= OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit= Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        mPodcastApi = retrofit.create(PodcastAPI::class.java)
    }

    override fun getRandomPodcast(): Observable<PodcastVO> {
        return mPodcastApi.getRandomPodcast()
    }


    override fun getUpNextPodcastList(id: String): Observable<GetPodcastListResponse> {
        return mPodcastApi.getUpNextPodcastList(id)
    }

    override fun getCategoryList(): Observable<GetCategoriesResponse> {
        return mPodcastApi.getCategoryList()
    }

    override fun getPodcastDetails(id: String): Observable<PodcastVO> {
        return mPodcastApi.getPodcastDetails(id)
    }
}