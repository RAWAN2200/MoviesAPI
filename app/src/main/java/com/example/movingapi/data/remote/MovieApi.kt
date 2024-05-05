package com.example.movingapi.data.remote

//import com.example.movingapi.BuildConfig
import com.example.movingapi.BuildConfig
import com.example.movingapi.model.SearchResponse
import org.intellij.lang.annotations.Language
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

     @GET("3/movie/upcoming")
     suspend fun getUpcoming(
          @Query("api_key")
          apiKey: String = BuildConfig.TMDB_API_KEY,
          @Query("language")
          language: String = "en-Us",
          @Query("page")
          page: Int = 1,

          ): Response<SearchResponse>

}