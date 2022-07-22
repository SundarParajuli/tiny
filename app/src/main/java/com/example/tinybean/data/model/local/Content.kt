package com.example.tinybean.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "contents")
data class Content(
    @PrimaryKey
    @ColumnInfo(name = "entryid") var id: String = UUID.randomUUID().toString(),
    @SerializedName("cols")
    val cols: Int = 0,
    @SerializedName("height")
    val height: Int = 0,
    @SerializedName("image")
    val image: List<Image> = mutableListOf(),
    @SerializedName("show")
    val show: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("type")

    val type: String = "",
    @SerializedName("url")

    val url: String = ""
)