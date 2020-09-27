package com.example.podcastapp.network.responses

import com.example.podcastapp.data.vos.GenresVO
import com.google.gson.annotations.SerializedName

data class GetCategoriesResponse(
    @SerializedName("genres") var genres : ArrayList<GenresVO> ?= null
)