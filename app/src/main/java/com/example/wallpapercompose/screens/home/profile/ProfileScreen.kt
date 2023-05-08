package com.example.wallpapercompose.screens.home.profile


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.painter.Painter

import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun ProfileScreen(navigator: DestinationsNavigator) {

    Column(modifier = Modifier.fillMaxSize()) {
        ProfileHeader()
        Spacer(modifier = Modifier.height(4.dp))
        ProfileDescription()
        Spacer(modifier = Modifier.height(10.dp))
        ProfilePosts()
    }
}

data class ImageWithText(
    val image: Painter,
    val text: String
)







