package com.example.podcastapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.podcastapp.data.vos.PodcastVO

@Dao
interface PodcastDao {

    @Query("SELECT * FROM podcasts")
    fun getRandomPodcast() : LiveData<PodcastVO>

    @Query("SELECT * FROM podcasts WHERE id= :id")
    fun getPodcastById(id: String): LiveData<PodcastVO>

    @Query("DELETE FROM podcasts")
    fun deleteAllPodcasts()

    @Delete
    fun deletePodcast(podcast : PodcastVO)

    @Insert
    fun insertAllPodcasts(podcasts: List<PodcastVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPodcast(podcast : PodcastVO)
}