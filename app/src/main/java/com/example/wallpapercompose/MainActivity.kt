package com.example.wallpapercompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.wallpapercompose.screens.NavGraphs
import com.example.wallpapercompose.screens.home.HomeScreen
import com.example.wallpapercompose.ui.theme.WallpaperComposeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.windowInsetsController!!.hide(
            android.view.WindowInsets.Type.statusBars()
        )
        setContent {
            WallpaperComposeTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

