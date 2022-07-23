package com.example.tinybean.data.source.repository

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.ImageContent

interface ContentRepository {
    suspend fun fetchContents(): Result<com.example.tinybean.data.model.dto.Content?>
    suspend fun fetchCarouselImage(url:String): ImageContent?
}