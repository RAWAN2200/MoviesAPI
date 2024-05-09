package com.example.movingapi.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movingapi.data.paging.MoviePagingSource
import com.example.movingapi.data.remote.MovieApi
import com.example.movingapi.model.MovieDetailsResponse
import com.example.movingapi.model.Results
import com.example.movingapi.model.UIState
import com.example.movingapi.model.UserAccount
import com.example.movingapi.model.UserTokenResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularMoviesRepository @Inject constructor(
    val movieApi: MovieApi
) {

    suspend fun getMovieDetails(movieID: Int): UIState<MovieDetailsResponse> {
        try {
            val response = movieApi.getMovieDetail(movieID)
            if (response.isSuccessful && response.body() != null) {
                return UIState.Success(response.body())
            } else {
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception) {
            return UIState.Error(e.message.toString())
        }

    }

    fun getUpComingMovies(): Flow<PagingData<Results>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                MoviePagingSource(movieApi,false)
            }
        ).flow
    }

    fun searchInMovies(query: String): Flow<PagingData<Results>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                MoviePagingSource(movieApi, true, query)
            }
        ).flow
    }
    suspend fun getUserToken(): UIState<UserTokenResponse>{
        try{
            val response = movieApi.getUserToken()
            if(response.isSuccessful && response.body() != null){
                return UIState.Success(response.body())
            } else {
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception){
            return UIState.Error(e.message.toString())
        }
    }
    suspend fun getSessionId(requestTokwn: String): UIState<UserTokenResponse>{
        try{
            val response = movieApi.getSessionId(requestToken = requestTokwn)
            if(response.isSuccessful && response.body() != null){
                return UIState.Success(response.body())
            } else {
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception){
            return UIState.Error(e.message.toString())
        }
    }
    suspend fun getUserAccount(sessionId: String): UIState<UserAccount>{
        try{
            val response = movieApi.getUserAccount(sessionId = sessionId)
            if(response.isSuccessful && response.body() != null){
                return UIState.Success(response.body())
            } else {
                return UIState.Empty(message = response.message().toString())
            }
        } catch (e: Exception){
            return UIState.Error(e.message.toString())
        }
    }


}