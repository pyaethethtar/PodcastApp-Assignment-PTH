package com.example.podcastapp.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName

@IgnoreExtraProperties
@Entity(tableName = "podcasts")
data class PodcastVO(

    @PrimaryKey
    @SerializedName("id") var id : String = "",
    @SerializedName("title") var title : String = "",
    @SerializedName("image") var image : String = "",
    @SerializedName("description") var description : String = "",
    @SerializedName("audio") var audio : String = "",
    @SerializedName("audio_length_sec")
    @get:PropertyName("audio_length_sec") @set:PropertyName("audio_length_sec") var audio_length : Int = 0
)