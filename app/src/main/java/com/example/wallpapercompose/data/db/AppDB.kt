package com.example.wallpapercompose.data.db

import androidx.room.Database
import androidx.room.RoomDatabase



@Database(entities = [WallpaperModelDB::class],exportSchema = false,version = 1)
abstract class AppDB: RoomDatabase() {
    abstract  fun  appDao(): AppDao
}