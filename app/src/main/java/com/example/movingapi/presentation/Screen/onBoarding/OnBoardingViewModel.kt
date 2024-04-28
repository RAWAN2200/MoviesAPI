package com.example.movingapi.presentation.Screen.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movingapi.domain.onBoarding.GetIsSafeFormDataStoreUseCase
import com.example.movingapi.domain.onBoarding.SaveIsFirstTimeDataStoreUseCase
import com.example.movingapi.presentation.navigation.Screens

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
        private val saveIsFirstTimeDataStoreUseCase: SaveIsFirstTimeDataStoreUseCase,
        private val getIsSafeFormDataStoreUseCase : GetIsSafeFormDataStoreUseCase
): ViewModel()
    {

        val onBoardingCompleted = MutableStateFlow(false)
        var startDestination: String = Screens.OnBoarding.route
        init {
            getonBoardingState()
        }

        private fun getonBoardingState() {
            viewModelScope.launch {
                onBoardingCompleted.value = getIsSafeFormDataStoreUseCase().stateIn(viewModelScope).value
                startDestination = if (onBoardingCompleted.value) Screens.PopularMovie.route else Screens.OnBoarding.route
            }
        }

        fun saveOnBoardingState(showOnBoardingPage: Boolean){
            viewModelScope.launch(Dispatchers.IO) {
                saveIsFirstTimeDataStoreUseCase(showTipsPage = showOnBoardingPage)
            }
        }
    }


