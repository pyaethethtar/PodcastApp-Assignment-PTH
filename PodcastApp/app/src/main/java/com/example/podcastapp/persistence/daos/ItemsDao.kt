package com.example.podcastapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.podcastapp.data.vos.GenresVO
import com.example.podcastapp.data.vos.ItemVO
import com.example.podcastapp.data.vos.PodcastVO

@Dao
interface ItemsDao {

    @Query("SELECT * FROM items")
    fun getAllItems() : LiveData<List<ItemVO>>

    @Query("SELECT * FROM items ORDER BY RANDOM() LIMIT 1 ")
    fun getRandomPodcast() : LiveData<ItemVO>

    @Query("SELECT * FROM items WHERE id = :id")
    fun getItemById(id: String): LiveData<ItemVO>

    @Query("DELETE FROM items")
    fun deleteAllItems()

    @Delete
    fun deleteItem(item : ItemVO)

    @Insert
    fun insertAllItems(items: List<ItemVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(item : ItemVO)
}