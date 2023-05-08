package com.example.wallpapercompose.screens.home.favorite

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapercompose.data.api.response_models.WallpaperModel
import com.example.wallpapercompose.data.db.WallpaperModelDB
import com.example.wallpapercompose.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class FavoriteVM @Inject constructor(
    private val  repository: FavoriteRepository
): ViewModel() {

    var  allWallpapers by mutableStateOf(emptyList<WallpaperModelDB>())


    fun getAllWallpapers() {

        viewModelScope.launch {
            repository.getAllFavorite.collect{
                allWallpapers=  it
            }
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAllWallpaper()
        }
    }

}