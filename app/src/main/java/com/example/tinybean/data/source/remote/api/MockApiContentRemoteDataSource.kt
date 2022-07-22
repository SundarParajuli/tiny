package com.example.tinybean.data.source.remote.api

import com.example.tinybean.data.Result
import com.example.tinybean.data.source.datasource.ContentDataSource
import com.example.tinybean.utils.safeApiCall
import com.example.tinybean.data.model.dto.Content
import javax.inject.Inject


class MockApiContentRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    ContentDataSource {
    override suspend fun fetchContents(): Result<Content?> = safeApiCall {
        val response = apiService.fetchContents()
        return@safeApiCall response.body()
    }
}

