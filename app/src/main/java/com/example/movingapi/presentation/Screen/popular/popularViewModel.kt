package com.example.movingapi.presentation.Screen.popular


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movingapi.domain.popular.GetPopularMoviesUseCase

import com.example.movingapi.model.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    var popularMoviesState: MutableStateFlow<PagingData<Results>> =
        MutableStateFlow(PagingData.empty())

    init {
        getArtWorks()
    }

    private fun getArtWorks() {
        viewModelScope.launch {
            getPopularMoviesUseCase.invoke().distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    popularMoviesState.value = it
                }
        }
    }
}