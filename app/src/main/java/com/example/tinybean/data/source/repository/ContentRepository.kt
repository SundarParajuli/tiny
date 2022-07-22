package com.example.tinybean.data.source.repository

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.ContentDto
import com.example.tinybean.data.model.local.Content

interface ContentRepository {
    suspend fun fetchContents(): Result<com.example.tinybean.data.model.dto.Content?>

}