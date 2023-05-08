package com.example.wallpapercompose.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallpapercompose.util.Constants


@Entity(tableName = Constants.Database_Table)
data class WallpaperModelDB(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val date: Long,
    val url:String,
    val urlPortrait:String,
)