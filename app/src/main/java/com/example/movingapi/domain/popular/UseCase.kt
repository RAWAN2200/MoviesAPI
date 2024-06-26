package com.example.movingapi.domain.popular

import androidx.paging.PagingData
import com.example.movingapi.data.repository.PopularMoviesRepository
import com.example.movingapi.model.Results
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class GetPopularMoviesUseCase @Inject constructor(private val popularMoviesRepository: PopularMoviesRepository) {
    operator fun invoke(): Flow<PagingData<Results>> {
        return popularMoviesRepository.getUpComingMovies()
    }
}