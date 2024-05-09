package com.example.movingapi.domain.Profile
import com.example.movingapi.data.repository.PopularMoviesRepository
import javax.inject.Inject

class GetSessionIdUseCase @Inject constructor(private val popularMoviesRepository: PopularMoviesRepository) {
    suspend operator fun invoke(requestToken: String) = popularMoviesRepository.getSessionId(requestToken)


}