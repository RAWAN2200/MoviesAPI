package com.example.movingapi.data.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movingapi.BuildConfig
import com.example.movingapi.data.remote.MovieApi
import com.example.movingapi.model.Results
import java.io.IOException

class MoviePagingSource(
    private val movieApi: MovieApi,
    private val isSearchEndPoint: Boolean,
    private val searchQuery: String? = null
) : PagingSource<Int, Results>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try {
            val currentPage = params.key ?: 1
            val movies = movieApi.getUpcoming(
                apiKey = BuildConfig.TMDB_API_KEY,
                page = currentPage
            )
            LoadResult.Page(
                data = movies.body()?.results.orEmpty(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.body()?.results?.isEmpty() == true) null else movies.body()?.page!! + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition
    }
}