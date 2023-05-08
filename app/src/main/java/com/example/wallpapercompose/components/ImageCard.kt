package com.example.wallpapercompose.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.wallpapercompose.data.api.response_models.WallpaperModel

@Composable
fun ImageCard(url: String,modifier :Modifier= Modifier) {

    SubcomposeAsyncImage(
        model = url,
        loading = {
            Box( contentAlignment = Alignment.Center, modifier =Modifier.size(65.dp)) {
                CircularProgressIndicator()
            }
        },
        contentScale = ContentScale.Crop,
        contentDescription = "",
        modifier = modifier
    )
}