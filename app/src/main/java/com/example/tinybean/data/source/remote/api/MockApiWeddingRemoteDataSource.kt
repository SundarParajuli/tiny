package com.example.tinybean.data.source.remote.api

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Image
import com.example.tinybean.data.source.datasource.WeddingDataSource
import com.example.tinybean.utils.safeApiCall
import javax.inject.Inject


class MockApiWeddingRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    WeddingDataSource {
    override suspend fun fetchWeddings(): Result<List<Image>?> = safeApiCall {
        val response = apiService.fetchWeddings()
        return@safeApiCall response.body()
    }
}

