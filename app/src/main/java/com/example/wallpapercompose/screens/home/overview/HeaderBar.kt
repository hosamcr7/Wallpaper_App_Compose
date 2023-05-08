package com.example.wallpapercompose.screens.home.overview

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpapercompose.R
import com.example.wallpapercompose.util.WallCategories
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun HeaderBar(navigator: DestinationsNavigator) {
    Column(        modifier= Modifier
        .padding(horizontal = paddingOverViewHorizontal)) {
        Row(
            horizontalArrangement= Arrangement.SpaceBetween,
            verticalAlignment= Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f)) {
                Text("Wallpaper X", style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary))
                Text(stringResource(id = R.string.get_premium ), style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary))
            }
            Surface(
                shape = RoundedCornerShape(15.dp),
                shadowElevation=8.dp,
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Rounded.Notifications, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(30.dp), contentDescription = "")
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        SearchBoxHome(navigator = navigator)
    }
}