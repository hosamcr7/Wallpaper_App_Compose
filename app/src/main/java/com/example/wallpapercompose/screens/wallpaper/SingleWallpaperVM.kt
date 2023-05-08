package com.example.wallpapercompose.screens.wallpaper

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapercompose.data.api.response_models.WallpaperModel
import com.example.wallpapercompose.data.db.WallpaperModelDB
import com.example.wallpapercompose.repository.FavoriteRepository
import com.example.wallpapercompose.repository.WallpaperRepository
import com.example.wallpapercompose.services.WallpaperService
import com.example.wallpapercompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class SingleWallpaperVM @Inject constructor(
    private val  favoriteRepository: FavoriteRepository, private val  repositoryApi: WallpaperRepository
): ViewModel() {
    val singleWallpaper : MutableState<Resource<WallpaperModel>> = mutableStateOf(Resource.Loading())
    val isInFavorite: MutableState<Boolean> = mutableStateOf(false)
    val loading: MutableState<Boolean> = mutableStateOf(false)

     suspend fun addToFavorite(model: WallpaperModel) {
        coroutineScope {
           if(isInFavorite.value) {
               favoriteRepository.deleteWallpaper(model.id)
           }
            else {
               favoriteRepository.addWallpaper(WallpaperModelDB(model.id,System.currentTimeMillis(),model.url,model.src.portrait))
            }
        }
    }

    suspend fun  getWallpaper(id: Int, image: WallpaperModel?){
        if(image!=null){
            singleWallpaper.value= Resource.Success(image)
        }else{
            viewModelScope.launch {
                singleWallpaper.value= withContext(Dispatchers.IO) {
                    repositoryApi.getWallpaperByID(id)
                }
            }
        }

    }

    fun getIsInFavorite(model: WallpaperModel) {
        viewModelScope.launch {
            favoriteRepository.getAllFavorite.collect{
                isInFavorite.value=  it.any {wall-> wall.id==model.id }
            }
        }
    }

    suspend fun setWallpaper(
        context: Context,
        imageURL: String,
    ): Boolean {
        val isDone: Boolean
        val  wallpaperService=WallpaperService()
        loading.value=true
        isDone =    withContext(Dispatchers.Default) {
             wallpaperService.setWallpaper(context =  context,imageURL)
        }
        loading.value=false
        return isDone
    }

    suspend fun downloadWallpaper(
        context: Context,
        imageURL: String,
    ): Boolean {
        val isDone: Boolean
        val  wallpaperService=WallpaperService()
        loading.value=true
        isDone =   withContext(Dispatchers.Default) {
         wallpaperService.saveImage(context =  context,imageURL)
        }
        loading.value=false
        return isDone
    }
}
