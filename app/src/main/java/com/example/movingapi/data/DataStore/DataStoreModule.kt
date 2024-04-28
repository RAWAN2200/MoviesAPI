package com.example.movingapi.data.DataStore

import android.content.Context
import com.example.movingapi.data.DataStore.MovieAppDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object DataStoreModule {
    @Module
    @InstallIn(SingletonComponent :: class)
    object dataStoeModel {

        @Provides
        @Singleton
        fun provideDataStoreOperations(
            @ApplicationContext context: Context
        ) = MovieAppDataStore(context = context)
    }
}