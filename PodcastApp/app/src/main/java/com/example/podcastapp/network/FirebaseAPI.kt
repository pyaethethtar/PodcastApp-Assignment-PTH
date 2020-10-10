package com.example.podcastapp.network

import com.example.podcastapp.data.vos.GenresVO
import com.example.podcastapp.data.vos.ItemVO
import com.example.podcastapp.data.vos.PodcastVO
import io.reactivex.Observable
import retrofit2.http.Path

interface FirebaseAPI {

    fun getRandomPodcast(onSuccess:(PodcastVO?)->Unit, onFailure:(String)->Unit)

    fun getUpNextPodcastList(onSuccess: (List<ItemVO>) -> Unit, onFailure: (String) -> Unit)

    fun getCategoryList(onSuccess: (List<GenresVO>) -> Unit, onFailure: (String) -> Unit)
}