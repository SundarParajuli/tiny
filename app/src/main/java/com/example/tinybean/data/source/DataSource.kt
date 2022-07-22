package com.example.tinybean.data.source

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.ContentDto
import com.example.tinybean.data.model.dto.Image

interface DataSource {
    suspend fun fetchContents(): Result<List<ContentDto>?>

    suspend fun fetchLists(): Result<List<Image>?>

    suspend fun fetchBrands(): Result<List<Image>?>

    suspend fun fetchWeddings(): Result<List<Image>?>

}