package com.example.wallpapercompose.data.api.response_models

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class PageResult(
    val page: Int,
    val per_page: Int,

    @SerializedName("photos")
    val photos: List<WallpaperModel>
)

@Parcelize
data class WallpaperModel(
    @SerializedName("avg_color")
    val avg_color: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("photographer")
    val photographer: String,

    @SerializedName("photographer_id")
    val photographer_id: Int,

    @SerializedName("photographer_url")
    val photographer_url: String,

    @SerializedName("src")
    val src: Src,

    @SerializedName("url")
    val url: String
) : Parcelable