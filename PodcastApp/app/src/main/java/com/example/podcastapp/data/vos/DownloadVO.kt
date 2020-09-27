package com.example.podcastapp.data.vos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloads")
data class DownloadVO (

    @PrimaryKey(autoGenerate = true)
    var downloadId : Int = 0,
    @Embedded
    var downloadVo : PodcastVO = PodcastVO()
){
    constructor(download: PodcastVO) : this(0, download)
}