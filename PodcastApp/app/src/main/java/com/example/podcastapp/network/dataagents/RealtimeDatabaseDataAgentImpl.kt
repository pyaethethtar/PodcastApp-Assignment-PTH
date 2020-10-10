package com.example.podcastapp.network.dataagents

import android.util.Log
import com.example.podcastapp.data.vos.GenresVO
import com.example.podcastapp.data.vos.ItemVO
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.network.FirebaseAPI
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


object RealtimeDatabaseDataAgentImpl : FirebaseAPI {

    private val database : DatabaseReference = Firebase.database.reference


    override fun getRandomPodcast(onSuccess: (PodcastVO?) -> Unit, onFailure: (String) -> Unit) {

    }

    override fun getUpNextPodcastList(
        onSuccess: (List<ItemVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("latest_episodes").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.e("error", error.message)
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                var podcastList = arrayListOf<ItemVO>()
               snapshot.children.forEach{ datasnapshot->
                   datasnapshot.getValue(PodcastVO::class.java)?.let {
                       var podcast = ItemVO(datasnapshot.key?.toInt(), it)
                       podcastList.add(podcast)
                   }
               }
                onSuccess(podcastList)
            }
        })
    }

    override fun getCategoryList(onSuccess: (List<GenresVO>) -> Unit, onFailure: (String) -> Unit) {

        database.child("genres").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.e("error", error.message)
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                var genresList = arrayListOf<GenresVO>()
                snapshot.children.forEach{ datasnapshot->
                    datasnapshot.getValue(GenresVO::class.java)?.let {
                        genresList.add(it)
                    }
                }
                onSuccess(genresList)
            }
        })
    }


}



