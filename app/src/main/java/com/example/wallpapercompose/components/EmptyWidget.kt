package com.example.wallpapercompose.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpapercompose.R

@Composable
fun EmptyWidget(text:String="No Data") {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        tonalElevation = 2.dp,
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(15.dp),

            ) {
            Icon(Icons.Rounded.List, modifier = Modifier.size(60.dp), contentDescription = "")
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = text,
                style = TextStyle(fontSize = 22.sp)
            )
        }
    }
}