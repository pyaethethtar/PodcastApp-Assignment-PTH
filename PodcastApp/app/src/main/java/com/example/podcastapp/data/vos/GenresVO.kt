package com.example.podcastapp.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName
@IgnoreExtraProperties
@Entity(tableName = "genres")
data class GenresVO (

    @PrimaryKey
    @SerializedName("id")
    @get:PropertyName("id") @set:PropertyName("id") var genresId : Int = 0,

    @SerializedName("name")
    @get:PropertyName("name") @set:PropertyName("name") var name : String = "",

    @SerializedName("parent_id")
    @get:PropertyName("parent_id") @set:PropertyName("parent_id") var parentId : Int = 0
)