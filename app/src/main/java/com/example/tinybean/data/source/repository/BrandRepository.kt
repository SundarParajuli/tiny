package com.example.tinybean.data.source.repository

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Image

interface BrandRepository {
    suspend fun fetchBrands(): Result<List<Image>?>
}