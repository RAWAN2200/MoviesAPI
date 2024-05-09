package com.example.movingapi.presentation.Screen.Search



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movingapi.domain.Search.GetPopularMoiveSearchUseCase
import com.example.movingapi.model.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchInMoviesUseCase: GetPopularMoiveSearchUseCase
) : ViewModel() {

    var moviesState: MutableStateFlow<PagingData<Results>> =
        MutableStateFlow(PagingData.empty())


    fun searchIntMovies(query: String) {
        viewModelScope.launch {
            searchInMoviesUseCase.invoke(query).distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collectIndexed { _, value ->
                    moviesState.value = value
                }
        }
    }
}