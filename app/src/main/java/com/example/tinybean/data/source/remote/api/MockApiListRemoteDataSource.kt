package com.example.tinybean.data.source.remote.api

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Images
import com.example.tinybean.data.source.datasource.ListDataSource
import com.example.tinybean.utils.safeApiCall
import javax.inject.Inject


class MockApiListRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    ListDataSource {
    override suspend fun fetchLists(): Result<Images?> = safeApiCall {
        val response = apiService.fetchLists()
        return@safeApiCall response.body()

    }
}

