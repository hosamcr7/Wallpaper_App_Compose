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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wallpapercompose.R
import com.example.wallpapercompose.components.ErrorWidget
import com.example.wallpapercompose.components.ImageCard
import com.example.wallpapercompose.components.ShimmerCard
import com.example.wallpapercompose.screens.destinations.SingleWallpaperScreenDestination
import com.example.wallpapercompose.util.Resource
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun BestOfTheMonth(navigator: DestinationsNavigator) {
    val viewModel = hiltViewModel<OverviewVM>()
    LaunchedEffect(true) {
        viewModel.getBestOfMonth()
    }

    Column() {

        Text(text = stringResource(id = R.string.best_of_the_month),modifier = Modifier.padding(horizontal = paddingOverViewHorizontal),
            style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(10.dp))



        Box(modifier = Modifier.height((LocalConfiguration.current.screenWidthDp *0.6).dp)){
           when(viewModel.wallBestOfMonth.value){

               is Resource.Error ->
                   ErrorWidget(text = viewModel.wallBestOfMonth.value.message?:"Error")


               is Resource.Loading -> LazyRow(contentPadding = PaddingValues(horizontal = paddingOverViewHorizontal),
                   content = { items(4) {
                       ShimmerCard(Modifier.padding(end = 10.dp)
                           .clip(RoundedCornerShape(15.dp))
                           .fillMaxHeight()
                           .width((LocalConfiguration.current.screenWidthDp * 0.4).dp))
                   } })

               is Resource.Success ->LazyRow(contentPadding = PaddingValues(horizontal = paddingOverViewHorizontal),
                   content = { items(viewModel.wallBestOfMonth.value.data?: emptyList()) { image->
                       ImageCard(image.src.portrait, modifier = Modifier.padding(end = 10.dp)
                           .clip(RoundedCornerShape(15.dp))
                           .fillMaxHeight()
                           .width((LocalConfiguration.current.screenWidthDp * 0.4).dp)
                           .clickable {  navigator.navigate(SingleWallpaperScreenDestination(
                               navArgs =  SingleWallpaperScreenDestination.NavArgs(image,image.id)
                           ))},

                       )
                   } })
           }
        }
    }

}




