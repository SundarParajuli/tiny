package com.example.tinybean.data.source.defaultRepository

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Image
import com.example.tinybean.data.source.datasource.WeddingDataSource
import com.example.tinybean.data.source.repository.WeddingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeddingRepositoryImpl(private val weddingRemoteDataSource: WeddingDataSource,
                            private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):WeddingRepository {
    override suspend fun fetchWeddings(): Result<List<Image>?> {
        val result = withContext(ioDispatcher){
            return@withContext weddingRemoteDataSource.fetchWeddings()
        }
        return result
    }
}