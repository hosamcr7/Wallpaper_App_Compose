package com.example.wallpapercompose.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.wallpapercompose.screens.home.favorite.FavoriteScreen
import com.example.wallpapercompose.screens.home.overview.OverViewScreen
import com.example.wallpapercompose.screens.home.profile.ProfileScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Destination(start = true)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val pages=BottomNavItem.values()
    Scaffold(
        bottomBar = {  BottomNavigationApp (onChangePage = {coroutineScope.launch{pagerState.animateScrollToPage(pages.indexOf(it))}   },bottomCurrentPage=pages[pagerState.currentPage]) }
    ) {
        HorizontalPager(count = pages.size,userScrollEnabled=false, state = pagerState,modifier = Modifier.padding(it)) { page ->
            PageScreen(navigator = navigator, bottomNavItem =pages[page] )
        }
    }

}

@Composable
fun PageScreen(navigator: DestinationsNavigator,bottomNavItem: BottomNavItem) {
     when (bottomNavItem){
        BottomNavItem.Overview -> OverViewScreen(navigator = navigator)
        BottomNavItem.Favorite -> FavoriteScreen(navigator = navigator)
        BottomNavItem.Profile -> ProfileScreen(navigator = navigator)
    }
}