package com.example.wallpapercompose.screens.home.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.wallpapercompose.R
import com.example.wallpapercompose.screens.destinations.CategoryScreenDestination
import com.example.wallpapercompose.util.WallColors
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun ColorTone(navigator: DestinationsNavigator) {
    Column() {
        Text(text = stringResource(id = R.string.color_tone),
            modifier = Modifier.padding(horizontal = paddingOverViewHorizontal),
            style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier.height(45.dp)){
            LazyRow(
                contentPadding = PaddingValues(horizontal = paddingOverViewHorizontal),
                content = { items(WallColors.values()) {
                Box(
                    modifier = Modifier
                        .clickable {  navigator.navigate(
                            CategoryScreenDestination(
                                it.name
                            )
                        ) }
                        .padding(end = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxHeight()
                        .width(45.dp)
                        .background(color = it.color())
                )
            } })
        }
    }

}