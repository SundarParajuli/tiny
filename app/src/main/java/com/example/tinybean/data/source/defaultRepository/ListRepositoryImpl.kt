package com.example.tinybean.data.source.defaultRepository

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Image
import com.example.tinybean.data.source.datasource.ListDataSource
import com.example.tinybean.data.source.repository.ListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListRepositoryImpl(private val listRemoteDataSource: ListDataSource,
                         private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):ListRepository {
    override suspend fun fetchLists(): Result<List<Image>?> {
        val result = withContext(ioDispatcher){
            return@withContext listRemoteDataSource.fetchLists()
        }
        return result
    }
}