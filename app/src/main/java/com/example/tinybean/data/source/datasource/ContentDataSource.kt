package com.example.tinybean.data.source.datasource

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Content

interface ContentDataSource {
    suspend fun fetchContents(): Result<Content?>

}