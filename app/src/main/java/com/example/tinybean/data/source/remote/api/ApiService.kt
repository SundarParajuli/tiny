package com.example.tinybean.data.source.remote.api

import com.example.tinybean.data.constant.API
import com.example.tinybean.data.model.dto.Image
import com.example.tinybean.data.model.dto.ImageContent
import com.example.tinybean.data.model.dto.Images
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

const val CACHE_CONTROL_HEADER = "Cache-Control"
const val CACHE_CONTROL_NO_CACHE = "no-cache"

interface ApiService {
    @GET(API.CONTENT)
    suspend fun fetchContents():Response<com.example.tinybean.data.model.dto.Content>

    @GET("{fullUrl}")
    suspend fun getCarouselImage(
        @Path(
            value = "fullUrl",
            encoded = true
        ) fullUrl: String?
    ): ImageContent

    @GET(API.LIST)
    suspend fun fetchLists():Response<Images>

    @GET(API.BRAND)
    suspend fun fetchBrands():Response<List<Image>>

    @GET(API.WEDDING)
    suspend fun fetchWeddings():Response<List<Image>>

}