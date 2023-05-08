package com.example.wallpapercompose.screens.home.favorite

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wallpapercompose.R
import com.example.wallpapercompose.screens.home.overview.paddingOverViewHorizontal

@Composable
fun FavoriteHeader() {
    val  viewModel = hiltViewModel<FavoriteVM>()

    Column(modifier= Modifier
        .padding(horizontal = paddingOverViewHorizontal)) {

        Row(
            horizontalArrangement= Arrangement.SpaceBetween,
            verticalAlignment= Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f)) {
                Text(stringResource(id = R.string.my_favorite ), style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary))
                Text("${viewModel.allWallpapers.size} Wallpaper", style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary))
            }
            Surface(
                shape = RoundedCornerShape(15.dp),
                shadowElevation=8.dp,
            ) {
                IconButton(onClick = { viewModel.deleteAll() }) {
                    Icon(Icons.Rounded.Delete, tint = Color.Red, modifier = Modifier.size(30.dp), contentDescription = "")
                }
            }
        }

    }
}