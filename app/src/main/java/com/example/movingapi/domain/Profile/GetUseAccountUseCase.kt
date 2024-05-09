package com.example.movingapi.domain.Profile

import com.example.movingapi.data.repository.PopularMoviesRepository
import javax.inject.Inject

class GetUseAccountUseCase @Inject constructor(private val popularMoviesRepository: PopularMoviesRepository) {

    suspend operator fun invoke(sessionId: String) = popularMoviesRepository.getUserAccount(sessionId)

}