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


object RealtimeDatabaseFirebase : FirebaseAPI {

    private val database : DatabaseReference = Firebase.database.reference


    override fun getRandomPodcast(onSuccess: (PodcastVO?) -> Unit, onFailure: (String) -> Unit) {

        database.child("latest_episodes").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.e("error", error.message)
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                var podcast = PodcastVO()
                snapshot.child("0").getValue(PodcastVO::class.java)?.let {
                    podcast = it
                }
                onSuccess(podcast)
            }
        })
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


//    var podcastVO : PodcastVO = PodcastVO("ab", "Hello", "https://sa1s3optim.patientpop.com/assets/images/provider/photos/2116667.jpg", "Hello Podcast App", "https://ia902500.us.archive.org/12/items/groks158/Groks031605.mp3", 2150)
//    var itemVO : ItemVO = ItemVO(0, podcastVO)
//    var podcastListResponse = GetPodcastListResponse("abc", arrayListOf(itemVO))
//
//    var observablePodcast = Observable.just(podcastVO)
//    var observablePodcastListResponse = Observable.just(podcastListResponse)
//
//
//
//    override fun getRandomPodcast(): Observable<PodcastVO> {
//        requestRandomPodcast()
//        return observablePodcast
//    }
//
//    override fun getUpNextPodcastList(id: String): Observable<GetPodcastListResponse> {
//        requestPodcastList()
//        return observablePodcastListResponse
//    }
//
//    override fun getCategoryList(): Observable<GetCategoriesResponse> {
//        var categoriesResponse : GetCategoriesResponse ?= GetCategoriesResponse()
//        return Observable.just(categoriesResponse)
//    }
//
//    override fun getPodcastDetails(id: String): Observable<PodcastVO> {
//        var podcast : PodcastVO ?= PodcastVO("abc", "Hello", "https://sa1s3optim.patientpop.com/assets/images/provider/photos/2116667.jpg", "Hello Podcast App", "https://ia902500.us.archive.org/12/items/groks158/Groks031605.mp3", 2150)
//        return Observable.just(podcast)
//    }
//
//    private fun passRandomPodcast(podcast : PodcastVO?){
//        observablePodcast = Observable.just(podcast)
//    }
//
//    private fun passPodcastListResponse(response: GetPodcastListResponse?){
//        observablePodcastListResponse = Observable.just(response)
//    }
//
//    private fun requestRandomPodcast(){
//
//        database.child("latest_episodes").addValueEventListener(object : ValueEventListener{
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("error", error.message)
//            }
//            override fun onDataChange(snapshot: DataSnapshot) {
//
//                val mpodcast = snapshot.child("0").getValue(PodcastVO::class.java)
//                passRandomPodcast(mpodcast)
//                Log.e("Snapshot", "init onDataChange")
//            }
//        })
//
//    }
//
//    private fun requestPodcastList(){
//        database.child("latest_episodes").addValueEventListener(object : ValueEventListener{
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("error", error.message)
//            }
//            override fun onDataChange(snapshot: DataSnapshot) {
//                var itemVOList = arrayListOf<ItemVO>()
//                var podcastListResponseId = "m1pe7z60bsw"
//
//                snapshot.children.forEach{datasnapshot ->
//                    datasnapshot.getValue(PodcastVO::class.java)?.let {
//
//                        var item = ItemVO(datasnapshot.key?.toInt(), it)
//                        itemVOList.add(item)
//
//                    }
//                }
//                var response = GetPodcastListResponse(podcastListResponseId, itemVOList)
//                passPodcastListResponse(response)
//                Log.e("Snapshot", response.items?.last()?.data?.title)
//
//            }
//        })
//    }



}



