package com.example.podcastapp.data.models

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.podcastapp.data.vos.DownloadVO
import com.example.podcastapp.data.vos.GenresVO
import com.example.podcastapp.data.vos.ItemVO
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.network.FirebaseAPI
import com.example.podcastapp.network.dataagents.CloudFirestoreDataAgentImpl
import com.example.podcastapp.network.dataagents.RealtimeDatabaseDataAgentImpl

object PodcastModelImpl : BaseModel() , PodcastModel{


    override var mPodcastApi: FirebaseAPI = CloudFirestoreDataAgentImpl


    @SuppressLint("CheckResult")
    override fun getPodcastsFromApiAndSaveToDatabase(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        mPodcastApi.getUpNextPodcastList(
            onSuccess = {
                mTheDB.itemDao().deleteAllItems()
                mTheDB.itemDao().insertAllItems(it)
            },
            onFailure = {
                Log.e("error", it)
            }
        )

        mPodcastApi.getCategoryList(
            onSuccess = {
                mTheDB.genresDao().deleteAllCategories()
                mTheDB.genresDao().insertAllCategories(it)
            },
            onFailure = {
                Log.e("error", it)
            }
        )

//        mPodcastApi.getRandomPodcast()
//            .subscribeOn(Schedulers.io())
//            .subscribe({
//                mTheDB.podcastDao().insertPodcast(it)
//            },{
//                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
//            })
//
//        mPodcastApi.getUpNextPodcastList("m1pe7z60bsw")
//            .map { it.items?.toList() ?: listOf()}
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.e("PodcastResult", it.last().data.id)
//                mTheDB.itemDao().insertAllItems(it)
//            },{
//                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
//                Log.e("PodcastResult", it.localizedMessage)
//            })
//
//        mPodcastApi.getCategoryList()
//            .map { it.genres?.toList() ?: listOf() }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Log.e("PodcastResult", it.first().genresId.toString())
//                mTheDB.genresDao().insertCategory(it.first())
//            },{
//                onError(it.localizedMessage ?: EM_NO_INTERNET_CONNECTION)
//                Log.e("PodcastResult", it.localizedMessage)
//            })

    }

    override fun getRandomPodcast(onError: (String) -> Unit): LiveData<ItemVO> {
       //return mTheDB.podcastDao().getRandomPodcast()
        return mTheDB.itemDao().getRandomPodcast()
    }

    override fun getUpNextPodcastList(onError: (String) -> Unit): LiveData<List<ItemVO>> {
        return mTheDB.itemDao().getAllItems()

    }

    override fun getAllCategories(onError: (String) -> Unit): LiveData<List<GenresVO>> {

        return mTheDB.genresDao().getAllCategories()
    }


    override fun getPodcastItemById(id: String): LiveData<ItemVO> {
        return  mTheDB.itemDao().getItemById(id)

    }

    override fun saveDownloadedData(data: PodcastVO) {

        var download = DownloadVO(data)
        mTheDB.downloadPodcastDao().insertDownloadedDataToDatabase(download)
    }

    override fun getDownloadedData() : LiveData<List<DownloadVO>>{
        return mTheDB.downloadPodcastDao().getDownloadedDataFromDatabase()
    }


}