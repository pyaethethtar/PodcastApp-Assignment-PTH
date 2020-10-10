package com.example.podcastapp.data.vos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName

@IgnoreExtraProperties
@Entity(tableName = "items")
data class ItemVO(

    @PrimaryKey
    @SerializedName("id") var itemId : Int ?= 0,
    @Embedded
    @SerializedName("data") var data : PodcastVO = PodcastVO()
)