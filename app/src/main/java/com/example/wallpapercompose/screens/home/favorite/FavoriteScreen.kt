package com.example.wallpapercompose.screens.home.favorite

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun FavoriteScreen(navigator: DestinationsNavigator) {
    val  viewModel = hiltViewModel<FavoriteVM>()

    LaunchedEffect(true){
        viewModel.getAllWallpapers()
    }

    Column {
        Spacer(modifier = Modifier.height(20.dp))
        FavoriteHeader()
        Spacer(modifier = Modifier.height(20.dp))
        FavoriteList(navigator = navigator )
    }


}