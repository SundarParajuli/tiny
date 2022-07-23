package com.example.tinybean.data.source.datasource

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Content
import com.example.tinybean.data.model.dto.ImageContent

interface ContentDataSource {
    suspend fun fetchContents(): Result<Content?>
    suspend fun fetchCarouselImages(url:String): ImageContent?
}