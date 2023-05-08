package com.example.wallpapercompose.screens.home.overview
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpapercompose.data.api.response_models.WallpaperModel
import com.example.wallpapercompose.repository.WallpaperRepository
import com.example.wallpapercompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OverviewVM @Inject constructor(
    private val  repository: WallpaperRepository
): ViewModel(){

     val wallBestOfMonth : MutableState<Resource<List<WallpaperModel>>> = mutableStateOf(Resource.Loading())

     suspend fun  getBestOfMonth(){
        viewModelScope.launch {
            wallBestOfMonth.value= withContext(Dispatchers.IO) {
                repository.getBestOfMonth(10)
            }
        }

     }




}