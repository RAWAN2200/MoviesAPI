package com.example.movingapi.domain.detail

import com.example.movingapi.data.repository.PopularMoviesRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val popularMoviesRepository: PopularMoviesRepository) {
    suspend operator fun invoke(id: Int) = popularMoviesRepository.getMovieDetails(id)

}