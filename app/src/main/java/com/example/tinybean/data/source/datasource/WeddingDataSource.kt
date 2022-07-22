package com.example.tinybean.data.source.datasource

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Image

interface WeddingDataSource {
    suspend fun fetchWeddings(): Result<List<Image>?>
}