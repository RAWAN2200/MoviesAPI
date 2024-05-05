package com.example.movingapi.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movingapi.data.paging.MoviePagingSource
import com.example.movingapi.data.remote.MovieApi
import com.example.movingapi.model.Results
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularMoviesRepository @Inject constructor(
    val movieApi: MovieApi
) {

    fun getPopularMovies(): Flow<PagingData<Results>> {
        return Pager(
            config = PagingConfig(pageSize = 15, prefetchDistance = 2),
            pagingSourceFactory = {
                MoviePagingSource(movieApi)
            }
        ).flow
    }
}