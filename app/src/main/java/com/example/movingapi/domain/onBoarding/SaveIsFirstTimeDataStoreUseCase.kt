package com.example.movingapi.domain.onBoarding


import com.example.movingapi.data.DataStore.MovieAppDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveIsFirstTimeDataStoreUseCase @Inject constructor(
    private val dataStore: MovieAppDataStore
){
    suspend operator fun invoke(showTipsPage: Boolean) {
        dataStore.saveOnBoardingState(showTipsPage)
    }
}