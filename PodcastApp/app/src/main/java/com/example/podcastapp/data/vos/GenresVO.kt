package com.example.podcastapp.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genres")
data class GenresVO (

    @PrimaryKey
    @SerializedName("id") var genresId : Int = 0,
    @SerializedName("parent_id") var parentId : Int = 0,
    @SerializedName("name") var name : String = ""
)