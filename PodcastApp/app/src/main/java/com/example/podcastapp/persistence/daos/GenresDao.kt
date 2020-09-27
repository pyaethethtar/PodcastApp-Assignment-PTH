package com.example.podcastapp.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.podcastapp.data.vos.GenresVO
import com.example.podcastapp.data.vos.PodcastVO

@Dao
interface GenresDao {

    @Query("SELECT * FROM genres")
    fun getAllCategories() : LiveData<List<GenresVO>>

    @Query("SELECT * FROM genres WHERE genresId= :id")
    fun getCategoryById(id : Int): LiveData<GenresVO>

    @Query("DELETE FROM genres")
    fun deleteAllCategories()

    @Delete
    fun deleteCategory(category : GenresVO)

    @Insert
    fun insertAllCategories(categories: List<GenresVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category : GenresVO)
}