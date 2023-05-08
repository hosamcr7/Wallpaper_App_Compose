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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpapercompose.R

@Composable
fun ErrorWidget(
    modifier: Modifier=Modifier,
    text:String="Error",
) {
    Surface(
        modifier = modifier.fillMaxSize()
            .wrapContentSize(Alignment.Center).padding(10.dp),
        tonalElevation = 2.dp,
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(15.dp),

            ) {
            Icon(painterResource(id = R.drawable.ic_error), modifier = Modifier.size(60.dp), contentDescription = "")
            Spacer(modifier = Modifier.height(5.dp))
            Text(

                text = text,
                textAlign= TextAlign.Center,
                style = TextStyle(fontSize = 22.sp)
            )
        }
    }
}