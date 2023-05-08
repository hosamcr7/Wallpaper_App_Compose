package com.example.wallpapercompose.screens.home.favorite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import com.example.wallpapercompose.R
import com.example.wallpapercompose.components.EmptyWidget
import com.example.wallpapercompose.components.ImageCard
import com.example.wallpapercompose.screens.destinations.SingleWallpaperScreenDestination
import com.example.wallpapercompose.screens.home.overview.paddingOverViewHorizontal
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
fun FavoriteList(navigator: DestinationsNavigator) {
    val  viewModel = hiltViewModel<FavoriteVM>()
    val wallpaperHeight: Dp = (LocalConfiguration.current.screenHeightDp *0.35).dp
    val items = viewModel.allWallpapers

    if(items.isEmpty()){
        EmptyWidget(stringResource(id = R.string.no_wallpaper))
    }
    else
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
       // contentPadding = PaddingValues(horizontal = paddingOverViewHorizontal),
        content = { items(items) { image->
            ImageCard(image.urlPortrait, modifier = Modifier
                .height(wallpaperHeight)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.background
                )
                .clip(RoundedCornerShape(0.dp))
                .clickable {
                    navigator.navigate(
                        SingleWallpaperScreenDestination(
                            navArgs = SingleWallpaperScreenDestination.NavArgs(null, image.id)
                        )
                    )
                },
            )
        }
        }

    )
}

