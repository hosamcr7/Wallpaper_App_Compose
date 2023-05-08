package com.example.wallpapercompose.data.db

import androidx.room.*
import androidx.room.Dao
import com.example.wallpapercompose.data.api.response_models.WallpaperModel
import com.example.wallpapercompose.util.Constants.Database_Table
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM $Database_Table order by id asc")
    fun getAllFavorite() : Flow<List<WallpaperModelDB>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend  fun addWallpaper(model: WallpaperModelDB)

    @Query("DELETE  FROM $Database_Table WHERE id=:id")
    suspend fun deleteWallpaper(id: Int)

    @Query("DELETE  FROM $Database_Table")
    suspend fun deleteAllWallpaper()
}