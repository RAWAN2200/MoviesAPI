package com.example.movingapi.domain.Search


import com.example.movingapi.data.repository.PopularMoviesRepository

import dagger.Reusable

import javax.inject.Inject


@Reusable
class GetPopularMoiveSearchUseCase @Inject constructor(private val popularMoviesRepository: PopularMoviesRepository) {
        operator fun invoke(query: String) = popularMoviesRepository.searchInMovies(query)
    }
