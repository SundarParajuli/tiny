package com.example.tinybean.repository

import com.example.tinybean.data.Result
import com.example.tinybean.data.model.dto.Image
import com.example.tinybean.data.model.dto.Images
import com.example.tinybean.data.source.repository.ListRepository

    private val fakeImageList = listOf(
        Image("image",120,"abc.com",123),
        Image("image2",10,"abc.com",13)
    )

class FakeListRepository:ListRepository {
    override suspend fun fetchLists(): Result<Images?> {
        return Result.Success(Images(fakeImageList))
    }
}