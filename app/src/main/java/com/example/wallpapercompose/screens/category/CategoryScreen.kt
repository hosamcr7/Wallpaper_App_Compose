package com.example.wallpapercompose.screens.category

import androidx.compose.animation.core.FloatExponentialDecaySpec
import androidx.compose.animation.core.generateDecayAnimationSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.wallpapercompose.components.ErrorWidget
import com.example.wallpapercompose.components.ImageCard
import com.example.wallpapercompose.components.ShimmerCard
import com.example.wallpapercompose.data.api.response_models.WallpaperModel
import com.example.wallpapercompose.screens.destinations.SingleWallpaperScreenDestination
import androidx.paging.compose.items

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CategoryScreen(catName:String="Category",navigator: DestinationsNavigator) {
    val wallpaperHeight: Dp = (LocalConfiguration.current.screenHeightDp *0.35).dp
    val viewModel = hiltViewModel<SingleCategoryVM>()
    viewModel.catName=catName
    val topAppBarState= rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        FloatExponentialDecaySpec(2f).generateDecayAnimationSpec(),topAppBarState)

    val lazyPagingItems = viewModel.singleCatWallpapers.collectAsLazyPagingItems()

    Scaffold(
        topBar = {CatAppBar(navigator, catName, scrollBehavior)},
        content = {
            Box(modifier = Modifier
                .padding(it)){

                if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .nestedScroll(scrollBehavior.nestedScrollConnection),
                        columns = GridCells.Fixed(2),
                        content = {
                            items(6) {
                                ShimmerCard(
                                    modifier = Modifier
                                        .height(wallpaperHeight)
                                        .border(
                                            width = 2.dp,
                                            color = MaterialTheme.colorScheme.background
                                        )
                                )
                            }
                        }
                    )
                }

                else if(lazyPagingItems.loadState.refresh is LoadState.Error || lazyPagingItems.loadState.append is LoadState.Error){
                    ErrorWidget(text ="Error")
                }

                else{
                    WallpapersGrid(lazyPagingItems ,wallpaperHeight = wallpaperHeight, navigator =navigator , scrollBehavior = scrollBehavior)
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WallpapersGrid(

    lazyPagingItems: LazyPagingItems<WallpaperModel>,
    wallpaperHeight: Dp,
    navigator: DestinationsNavigator,
    scrollBehavior: TopAppBarScrollBehavior
) {
    val state: LazyGridState = rememberLazyGridState()

        LazyVerticalGrid(
            state = state,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            columns = GridCells.Fixed(2)
        )
        {
            items(lazyPagingItems) { image ->
                if (image != null) {
                    ImageCard(
                        image.src.portrait,
                        modifier = Modifier
                            .height(wallpaperHeight)
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.background
                            )
                            .clickable {
                                navigator.navigate(
                                    SingleWallpaperScreenDestination(
                                        navArgs = SingleWallpaperScreenDestination.NavArgs(
                                            image,
                                            image.id
                                        )
                                    )
                                )
                            },
                    )
                }
            }
            if (lazyPagingItems.loadState.append == LoadState.Loading) {
                items(2) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }

        }

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CatAppBar(
    navigator: DestinationsNavigator,
    catName: String,
    scrollBehavior: TopAppBarScrollBehavior
)  {
    MediumTopAppBar(

        scrollBehavior=scrollBehavior,
        navigationIcon = {
            IconButton(
                onClick = { navigator.navigateUp() },
                content = {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = ""
                    )
                })
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = ""
                )
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = ""
                )
            }

        },
        title = { Text(text = "$catName Wallpapers") },
    )
}













//TODO: remove these functions when Paginate library support LazyVerticalGrid
inline fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    crossinline itemContent: @Composable LazyGridItemScope.(item: T?) -> Unit
) {
    items(count = items.itemCount) { index ->
        itemContent(items[index])
    }
}

inline fun <T : Any> LazyGridScope.items(
    items: LazyPagingItems<T>,
    noinline key: ((item: T?) -> Any)? = null,
    noinline span: (LazyGridItemSpanScope.(item: T?) -> GridItemSpan)? = null,
    noinline contentType: (item: T?) -> Any? = { null },
    crossinline itemContent: @Composable LazyGridItemScope.(item: T?) -> Unit
) = items(
    count = items.itemCount,
    key = if (key != null) { index: Int -> key(items[index]) } else null,
    span = if (span != null) { { span(items[it]) } } else null,
    contentType = { index: Int -> contentType(items[index]) }
) {
    itemContent(items[it])
}


