package com.example.wallpapercompose.repository

import com.example.wallpapercompose.data.api.WallpaperApi
import com.example.wallpapercompose.data.api.response_models.WallpaperModel
import com.example.wallpapercompose.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class WallpaperRepository  @Inject constructor(
    private val api: WallpaperApi
) {

    suspend fun getWallpaperByID(id: Int): Resource<WallpaperModel> {
        val response = try {
            api.getWallpaperByID(id)
        } catch(e: Exception) {
            return Resource.Error(e.message?:"An unknown error occurred")
        }
        return Resource.Success(response)
    }


    suspend fun getBestOfMonth(limit: Int): Resource<List<WallpaperModel>> {
        val response = try {
            api.getBestOfMonthWallpapers(limit)
        } catch(e: Exception) {
            return Resource.Error(e.message?:"An unknown error occurred")
        }
        return Resource.Success(response.photos)
    }

    suspend fun getWallpaperByCategory(limit: Int, catName: String, page: Int = 0): Resource<List<WallpaperModel>> {
        val response = try {
            api.getSearchedWallpapers(catName,limit,page)
        } catch(e: Exception) {
            return Resource.Error(e.message?:"An unknown error occurred")
        }
        return Resource.Success(response.photos)
    }

}