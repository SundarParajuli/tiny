package com.example.tinybean.data.source.repository

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Image

interface WeddingRepository {
    suspend fun fetchWeddings(): Result<List<Image>?>
}