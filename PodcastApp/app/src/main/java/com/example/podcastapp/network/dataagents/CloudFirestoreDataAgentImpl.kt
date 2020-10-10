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

object CloudFirestoreDataAgentImpl : FirebaseAPI {

    private val db = Firebase.firestore
    var i : Int = 0

    override fun getRandomPodcast(onSuccess: (PodcastVO?) -> Unit, onFailure: (String) -> Unit) {

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
                for (document in results){
                    val item = typecastPodcastVO(document.data)
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
                    val genresVO = typecastGenresVO(document.data)
                    genresList.add(genresVO)
                }
                onSuccess(genresList)
            }
        }
    }

    private fun typecastPodcastVO(data : MutableMap<String, Any>?) : ItemVO{
        var podcastVO = PodcastVO()
        podcastVO.id = data?.get("id") as String
        podcastVO.title = data?.get("title") as String
        podcastVO.description = data["description"] as String
        podcastVO.image = data?.get("image") as String
        podcastVO.audio = data["audio"] as String
        podcastVO.audio_length = (data["audio_length_sec"] as Long).toInt()
        return ItemVO(i, podcastVO)
    }

    private fun typecastGenresVO(data : MutableMap<String, Any>?) : GenresVO{
        var genresVO = GenresVO()
        genresVO.genresId = (data?.get("id") as Long).toInt()
        genresVO.name = data["name"] as String
        genresVO.parentId = (data?.get("parent_id") as Long).toInt()
        return genresVO
    }


}