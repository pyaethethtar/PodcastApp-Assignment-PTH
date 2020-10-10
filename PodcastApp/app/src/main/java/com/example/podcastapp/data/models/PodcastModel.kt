package com.example.podcastapp.data.models

import androidx.lifecycle.LiveData
import com.example.podcastapp.data.vos.DownloadVO
import com.example.podcastapp.data.vos.GenresVO
import com.example.podcastapp.data.vos.ItemVO
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.network.FirebaseAPI
import com.example.podcastapp.network.PodcastAPI

interface PodcastModel {

    var mPodcastApi : FirebaseAPI

    fun getPodcastsFromApiAndSaveToDatabase(onSuccess: ()->Unit, onError: (String)->Unit)
//    fun getRandomPodcast(onError: (String)->Unit) : LiveData<PodcastVO>
    fun getRandomPodcast(onError: (String)->Unit) : LiveData<ItemVO>
    fun getUpNextPodcastList(onError: (String)->Unit) : LiveData<List<ItemVO>>
    fun getAllCategories(onError: (String) -> Unit) : LiveData<List<GenresVO>>
    fun getPodcastItemById(id: String) : LiveData<ItemVO>

    fun saveDownloadedData(data : PodcastVO)
    fun getDownloadedData() : LiveData<List<DownloadVO>>
}