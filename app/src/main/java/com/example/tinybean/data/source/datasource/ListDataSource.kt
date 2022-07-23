package com.example.tinybean.data.source.datasource

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Images

interface ListDataSource {
    suspend fun fetchLists(): Result<Images?>
}