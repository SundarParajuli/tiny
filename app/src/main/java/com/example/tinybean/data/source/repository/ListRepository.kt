package com.example.tinybean.data.source.repository

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Images

interface ListRepository {
    suspend fun fetchLists(): Result<Images?>
}