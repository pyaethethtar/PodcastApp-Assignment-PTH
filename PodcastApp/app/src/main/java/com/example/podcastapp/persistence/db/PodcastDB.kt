package com.example.podcastapp.persistence.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.podcastapp.data.vos.DownloadVO
import com.example.podcastapp.data.vos.GenresVO
import com.example.podcastapp.data.vos.ItemVO
import com.example.podcastapp.data.vos.PodcastVO
import com.example.podcastapp.persistence.daos.DownloadDao
import com.example.podcastapp.persistence.daos.GenresDao
import com.example.podcastapp.persistence.daos.ItemsDao
import com.example.podcastapp.persistence.daos.PodcastDao

@Database(entities = [PodcastVO::class, GenresVO::class, ItemVO::class, DownloadVO::class], version = 9, exportSchema = false)
abstract class PodcastDB : RoomDatabase(){

    companion object{

        val DB_NAME = "Podcast_DB"
        var dbInstance: PodcastDB?=null

        fun getDBInstance(context: Context): PodcastDB {
            when(dbInstance){
                null -> {
                    dbInstance = Room.databaseBuilder(context, PodcastDB::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            val i= dbInstance
            return i!!
        }
    }

    abstract fun podcastDao() : PodcastDao
    abstract fun genresDao() : GenresDao
    abstract fun itemDao() : ItemsDao
    abstract fun downloadPodcastDao() : DownloadDao
}