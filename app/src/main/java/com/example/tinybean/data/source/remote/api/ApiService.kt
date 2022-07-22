package com.example.tinybean.data.source.remote.api

import com.example.tinybean.data.constant.API
import com.example.tinybean.data.model.dto.Image
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(API.CONTENT)
    suspend fun fetchContents():Response<com.example.tinybean.data.model.dto.Content>

    @GET(API.LIST)
    suspend fun fetchLists():Response<List<Image>>

    @GET(API.BRAND)
    suspend fun fetchBrands():Response<List<Image>>

    @GET(API.WEDDING)
    suspend fun fetchWeddings():Response<List<Image>>

}