package com.example.movingapi.domain.Profile

import com.example.movingapi.data.repository.PopularMoviesRepository
import javax.inject.Inject

class GetUserTokenUseCase @Inject constructor(private val popularMoviesRepository: PopularMoviesRepository) {
    suspend operator fun invoke() =
        popularMoviesRepository.getUserToken()
}