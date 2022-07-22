package com.example.tinybean.data.source.datasource

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Image

interface BrandDataSource {
    suspend fun fetchBrands(): Result<List<Image>?>
}