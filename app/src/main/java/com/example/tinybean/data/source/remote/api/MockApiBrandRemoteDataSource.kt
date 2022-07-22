package com.example.tinybean.data.source.remote.api

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Image
import com.example.tinybean.data.source.datasource.BrandDataSource
import com.example.tinybean.utils.safeApiCall
import javax.inject.Inject


class MockApiBrandRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    BrandDataSource {


    override suspend fun fetchBrands(): Result<List<Image>?> = safeApiCall {
        val response = apiService.fetchBrands()
        return@safeApiCall response.body()
    }



}

