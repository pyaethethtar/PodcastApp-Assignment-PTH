package com.example.podcastapp.network

import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.network.responses.GetCategoriesResponse
import com.example.podcastapp.network.responses.GetPodcastListResponse
import com.example.podcastapp.utils.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface PodcastAPI {

    @GET(GET_RANDOM_PODCAST_EPISODE)
    fun getRandomPodcast() : Observable<PodcastVO>

    @GET("$GET_PLAYLIST_INFO_AND_ITEMS{ID}$GET_PLAYLIST_INFO_AND_ITEMS_2")
    fun getUpNextPodcastList(@Path("ID") id : String) : Observable<GetPodcastListResponse>

    @GET(GET_GENRES_LIST)
    fun getCategoryList() : Observable<GetCategoriesResponse>

    @GET("$GET_PODCAST_DETAILS={ID}")
    fun getPodcastDetails(@Path("ID") id : String) : Observable<PodcastVO>
}