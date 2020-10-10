package com.example.podcastapp.network.dataagents

import android.content.ClipData
import android.util.Log
import com.example.podcastapp.data.vos.GenresVO
import com.example.podcastapp.data.vos.ItemVO
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.network.FirebaseAPI
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object CloudFirestore : FirebaseAPI {

    private val db = Firebase.firestore

    override fun getRandomPodcast(onSuccess: (PodcastVO?) -> Unit, onFailure: (String) -> Unit) {
        db.collection("latest_episodes").addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: "Please check your connection")
            } ?: run {
                val result = value?.documents?.first()?.data
                var podcastVO = PodcastVO()
                podcastVO.id = result?.get("id") as String
                podcastVO.title = result?.get("title") as String
                podcastVO.description = result["description"] as String
                podcastVO.image = result?.get("image") as String
                podcastVO.audio = result["audio"] as String
                podcastVO.audio_length = (result["audio_length_sec"] as Long).toInt()

                onSuccess(podcastVO)
            }
        }
    }

    override fun getUpNextPodcastList(
        onSuccess: (List<ItemVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("latest_episodes").addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: "Please check your connection")
            } ?: run {
                var podcastList = arrayListOf<ItemVO>()
                val results = value?.documents ?: arrayListOf()
                var i : Int = 0
                for (document in results){
                    val data = document.data
                    var podcastVO = PodcastVO()
                    podcastVO.id = data?.get("id") as String
                    podcastVO.title = data?.get("title") as String
                    podcastVO.description = data["description"] as String
                    podcastVO.image = data?.get("image") as String
                    podcastVO.audio = data["audio"] as String
                    podcastVO.audio_length = (data["audio_length_sec"] as Long).toInt()
                    var item = ItemVO(i, podcastVO)
                    podcastList.add(item)
                    i++
                }
                onSuccess(podcastList)
            }
        }
    }

    override fun getCategoryList(onSuccess: (List<GenresVO>) -> Unit, onFailure: (String) -> Unit) {
        db.collection("genres").addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: "Please check your connection")
            } ?: run {
                var genresList = arrayListOf<GenresVO>()
                val results = value?.documents ?: arrayListOf()
                for (document in results){
                    val data = document.data
                    var genresVO = GenresVO()
                    genresVO.genresId = (data?.get("id") as Long).toInt()
                    genresVO.name = data["name"] as String
                    genresVO.parentId = (data?.get("parent_id") as Long).toInt()

                    genresList.add(genresVO)
                }
                onSuccess(genresList)
            }
        }
    }


}