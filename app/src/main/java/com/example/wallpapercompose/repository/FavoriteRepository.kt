package com.example.wallpapercompose.repository

import com.example.wallpapercompose.data.db.AppDao
import com.example.wallpapercompose.data.db.WallpaperModelDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject



class FavoriteRepository @Inject constructor(private val appDao:AppDao) {

    val getAllFavorite: Flow<List<WallpaperModelDB>> =appDao.getAllFavorite()

    suspend  fun addWallpaper(model: WallpaperModelDB){appDao.addWallpaper(model)}

    suspend fun deleteWallpaper(id: Int){appDao.deleteWallpaper(id)}

    suspend fun deleteAllWallpaper(){appDao.deleteAllWallpaper()}

}