package com.example.wallpapercompose.screens.home.overview


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


val  paddingOverViewHorizontal=15.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverViewScreen(navigator: DestinationsNavigator) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Column() {
                Spacer(modifier = Modifier.height(20.dp))
                HeaderBar(navigator = navigator)
                Spacer(modifier = Modifier.height(30.dp))
                BestOfTheMonth(navigator)
                Spacer(modifier = Modifier.height(30.dp))
                ColorTone(navigator)
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
        item {
            HomeCategories(navigator)
        }
    }

}