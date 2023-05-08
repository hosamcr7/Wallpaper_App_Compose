package com.example.wallpapercompose.screens.wallpaper

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.wallpapercompose.R
import com.example.wallpapercompose.components.ErrorWidget
import com.example.wallpapercompose.components.ShimmerCard
import com.example.wallpapercompose.data.api.response_models.WallpaperModel
import com.example.wallpapercompose.util.Resource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun SingleWallpaperScreen(navigator: DestinationsNavigator, image: WallpaperModel?,id:Int) {

    val viewModel = hiltViewModel<SingleWallpaperVM>()
    LaunchedEffect(true){
        viewModel.getWallpaper(id,image)
    }

    when(viewModel.singleWallpaper.value){

        is Resource.Error ->
            ErrorWidget(text = viewModel.singleWallpaper.value.message?:"Error")


        is Resource.Loading ->
            Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }

        is Resource.Success ->
            WallpaperContent(navigator = navigator, image =viewModel.singleWallpaper.value.data!!,viewModel)
    }



}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
private  fun WallpaperContent(navigator: DestinationsNavigator,image: WallpaperModel,viewModel:SingleWallpaperVM= hiltViewModel()) {
    val openDialog = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(true){
        viewModel.getIsInFavorite(image)
    }
    Scaffold(
        snackbarHost ={ SnackbarHost(snackbarHostState) } ,
        content = {
            BoxWithConstraints(
                Modifier.padding(it)
            ) {

                AboutWallpaperDialog(isOpen = openDialog.value, wallpaperModel = image ,onDismiss =  { openDialog.value = false })


                SubcomposeAsyncImage(
                    model = image.src.portrait,
                    loading = {
                        ShimmerCard()
                    },
                    contentScale= ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )

                Surface(
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.TopStart)
                ) {
                    IconButton(
                        onClick = { navigator.navigateUp()},
                        content=   { Icon(Icons.Default.ArrowBack,
                            modifier = Modifier
                                .size(30.dp),
                            contentDescription ="" )
                        })
                }

                Surface(
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.TopEnd)
                ) {
                    IconButton(
                        onClick = {
                            coroutineScope.launch { viewModel.addToFavorite(image)  }
                        },
                        content=   {
                            AnimatedContent(targetState = viewModel.isInFavorite.value) {
                                Icon(
                                    if(viewModel.isInFavorite.value) Icons.Default.Favorite
                                    else Icons.Default.FavoriteBorder , tint = Color.Red,
                                    modifier = Modifier
                                        .size(30.dp),
                                    contentDescription ="" )
                            }

                        }
                    )
                }

                AnimatedContent(targetState = viewModel.loading, Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)) {bool->
                    if(bool.value){
                        LinearProgressIndicator()
                    }
                    else{
                        Row(
                            horizontalArrangement =  Arrangement.SpaceEvenly) {

                            Surface(shape = RoundedCornerShape(8.dp)) {
                                Image(painterResource(id = R.drawable.info_ic),
                                    modifier= Modifier
                                        .clickable { openDialog.value = true }
                                        .padding(5.dp)
                                        .size(40.dp), contentDescription = "")
                            }


                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                shadowElevation=0 .dp,
                                modifier= Modifier
                                    .clickable {
                                        coroutineScope.launch {

                                            val isDone = viewModel.setWallpaper(
                                                context,
                                                image.src.portrait,
                                            )
                                            if (isDone) {
                                                snackbarHostState.showSnackbar(message = "Set Wallpaper Successfully")
                                            } else {
                                                snackbarHostState.showSnackbar(message = "Error While Set Wallpaper")
                                            }
                                        }

                                    }
                                    .width(150.dp)
                                    .height(50.dp)
                                    .border(
                                        0.dp, color = MaterialTheme.colorScheme.primary,
                                        RoundedCornerShape(8.dp)
                                    ),
                                color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
                            ) {
                                Text(
                                    text = "Apply",
                                    textAlign = TextAlign.Center,
                                    modifier= Modifier
                                        .fillMaxSize()
                                        .wrapContentHeight()
                                        .padding(5.dp),
                                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = MaterialTheme.colorScheme.onSecondary))
                            }

                            Surface(shape = RoundedCornerShape(8.dp)) {
                                Image(painterResource(id = R.drawable.cloud_ic),
                                    modifier= Modifier
                                        .clickable {
                                            coroutineScope.launch {
                                                val isDone = viewModel.downloadWallpaper(
                                                    context,
                                                    image.src.portrait,
                                                )
                                                if (isDone) {
                                                    snackbarHostState.showSnackbar(message = "Wallpaper Downloaded Successfully")
                                                } else {
                                                    snackbarHostState.showSnackbar(message = "Error While Download Wallpaper")
                                                }
                                            }
                                        }
                                        .padding(5.dp)
                                        .size(40.dp), contentDescription = "")
                            }
                        }
                    }
                }

            }
        }
    )
}

