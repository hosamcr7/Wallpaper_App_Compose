package com.example.wallpapercompose.data.api

import com.example.wallpapercompose.data.api.response_models.PageResult
import com.example.wallpapercompose.data.api.response_models.WallpaperModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WallpaperApi {
    @GET("curated")
    suspend fun getBestOfMonthWallpapers(
        @Query("per_page") perPage: Int=10
    ): PageResult

    @GET("photos/{id}")
    suspend fun getWallpaperByID(
        @Path("id") id: Int
    ): WallpaperModel


    @GET("search")
    suspend fun getSearchedWallpapers(
        @Query("query") searchText: String = "",
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 0,
    ): PageResult


}