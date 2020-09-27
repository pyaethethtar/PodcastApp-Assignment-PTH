package com.example.podcastapp.network.responses

import com.example.podcastapp.data.vos.ItemVO
import com.example.podcastapp.data.vos.PodcastVO
import com.google.gson.annotations.SerializedName

data class GetPodcastListResponse(
    @SerializedName("id") var id : String = "",
    @SerializedName("name") var name: String = "",
    @SerializedName("items") var items: ArrayList<ItemVO> ?= null
)