package com.example.wallpapercompose.screens.home.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wallpapercompose.R

@Composable
fun ProfileHeader() {
    val heightMax=220.dp
    val logoHeight = 110.dp

    BoxWithConstraints(
      Modifier.height(heightMax)
    ){
        val maxHeight = this.maxHeight

        Image(
            painter = painterResource(id = R.drawable.city),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(heightMax - (logoHeight / 2))
                .fillMaxWidth()
        )

        Image(
            painter = painterResource(id = R.drawable.myphoto),
            contentDescription = null,
            contentScale= ContentScale.FillWidth,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, end = 20.dp)
                .size(logoHeight)
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.background,
                    shape = CircleShape
                )
                .clip(CircleShape)
        )
        Box(modifier = Modifier.align(Alignment.BottomEnd).padding(start = 10.dp, bottom = (logoHeight / 4), end = 10.dp)){
            Row() {
                Surface(
                    shape = CircleShape,
                    shadowElevation=3.dp,
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Rounded.Edit, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(25.dp), contentDescription = "")
                    }
                }
                Spacer(modifier = Modifier.width(15.dp))

                Surface(
                    shape = CircleShape,
                    shadowElevation =3.dp,
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Rounded.Place, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(25.dp), contentDescription = "")
                    }
                }
            }
        }
    }
}