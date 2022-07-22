package com.example.tinybean.data.model.local

import androidx.room.Entity

@Entity(tableName = "lists")
data class Image(
    val format: String,
    val height: Int,
    val url: String,
    val width: Int
)