package com.example.wallpapercompose.screens.category
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.wallpapercompose.data.api.response_models.WallpaperModel
import com.example.wallpapercompose.repository.WallpaperRepository
import com.example.wallpapercompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SingleCategoryVM @Inject constructor(
    private val  repository: WallpaperRepository,
): ViewModel() {
      var catName=""
      var   singleCatWallpapers = Pager(PagingConfig(pageSize = 10)) {
        WallpaperPaginate(repository,catName)
    }.flow.cachedIn(viewModelScope)

}

class WallpaperPaginate  constructor(
    private val  repository: WallpaperRepository,
    private val  catName:String
    ) : PagingSource<Int, WallpaperModel>() {

    override fun getRefreshKey(state: PagingState<Int, WallpaperModel>): Int?
    {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>):  LoadResult<Int, WallpaperModel> {

            val nextPage = params.key ?: 1
            val res = withContext(Dispatchers.IO){
                repository.getWallpaperByCategory(10,catName=catName,page = nextPage)
            }
        return  if(res is Resource.Success){
             val wallList =res.data?: emptyList()

              LoadResult.Page(
                data = wallList,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (wallList.isEmpty()) null else nextPage + 1
            )
        }else{
            return LoadResult.Error(Throwable(message = res.message) )
        }


    }
}