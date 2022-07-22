package com.example.tinybean.data.model.dto

data class ContentDto(
    val cols: Int,
    val height: Int,
    val images: List<Image>,
    val show: Int,
    val title: String,
    val type: String,
    val url: String
)