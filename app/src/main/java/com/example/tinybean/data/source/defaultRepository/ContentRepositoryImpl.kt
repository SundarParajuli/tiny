package com.example.tinybean.data.source.defaultRepository

import com.example.tinybean.data.Result
import com.example.tinybean.data.source.datasource.ContentDataSource
import com.example.tinybean.data.source.repository.ContentRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.tinybean.data.model.dto.Content
import com.example.tinybean.data.model.dto.ImageContent

class ContentRepositoryImpl(private val contentRemoteDataSource: ContentDataSource,
                            private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):ContentRepository {
    override suspend fun fetchContents(): Result<Content?> {
        val result = withContext(ioDispatcher){
            return@withContext contentRemoteDataSource.fetchContents()
        }
        return result
    }

    override suspend fun fetchCarouselImage(url: String): ImageContent? {
        val result = withContext(ioDispatcher) {
            return@withContext contentRemoteDataSource.fetchCarouselImages(url)
        }
        return result
    }
}