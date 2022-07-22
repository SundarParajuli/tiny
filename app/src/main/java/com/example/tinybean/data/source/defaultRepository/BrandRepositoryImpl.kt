package com.example.tinybean.data.source.defaultRepository

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Image
import com.example.tinybean.data.source.datasource.BrandDataSource
import com.example.tinybean.data.source.repository.BrandRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BrandRepositoryImpl(private val brandRemoteDataSource: BrandDataSource,
                          private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):BrandRepository {
    override suspend fun fetchBrands(): Result<List<Image>?> {
        val result = withContext(ioDispatcher){
            return@withContext brandRemoteDataSource.fetchBrands()
        }
        return result
    }
}