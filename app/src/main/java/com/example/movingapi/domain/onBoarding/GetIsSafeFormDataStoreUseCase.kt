package com.example.movingapi.domain.onBoarding


import com.example.movingapi.data.DataStore.MovieAppDataStore
import com.example.movingapi.data.repository.PopularMoviesRepository
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


class GetIsSafeFormDataStoreUseCase @Inject constructor(
    private val dataStor: MovieAppDataStore
)  {
    operator fun invoke(): Flow<Boolean> {
        return dataStor.readonBoardingSate()
    }
}