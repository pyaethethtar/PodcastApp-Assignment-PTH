package com.example.podcastapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.podcastapp.data.vos.DownloadVO
import com.example.podcastapp.data.vos.PodcastVO

@Dao
interface DownloadDao {

    @Query("SELECT * FROM downloads")
    fun getDownloadedDataFromDatabase() : LiveData<List<DownloadVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDownloadedDataToDatabase(data : DownloadVO)
}