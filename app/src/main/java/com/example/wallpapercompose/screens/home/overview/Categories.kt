package com.example.wallpapercompose.screens.home.overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wallpapercompose.R
import com.example.wallpapercompose.screens.destinations.CategoryScreenDestination
import com.example.wallpapercompose.util.WallCategories
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeCategories(navigator:DestinationsNavigator) {
    Column(modifier = Modifier.padding(horizontal = paddingOverViewHorizontal)) {
        Text(text = stringResource(id = R.string.categories),

            style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(
            mainAxisSize = SizeMode.Expand,
            crossAxisSpacing =10.dp ,
            mainAxisSpacing =6.dp ,

            mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
        ) {
            val itemSize: Dp = (LocalConfiguration.current.screenWidthDp *0.435).dp

            for (cat in WallCategories.values()) {
                BoxWithConstraints(
                    Modifier
                        .clickable {
                            navigator.navigate(CategoryScreenDestination(cat.name))
                        }
                        .width(itemSize)
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    Image( painter  = painterResource(id = cat.imageSrc()), contentDescription =cat.name )
                    //  Box(modifier = Modifier.fillParentMaxWidth().background(color =Color.Black.copy(alpha = 0.7f)))
                    Text(text = cat.name,style= TextStyle(fontWeight = FontWeight.Bold, fontSize =20.sp, color = Color.White), modifier = Modifier.align(Alignment.Center))
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))



    }

}