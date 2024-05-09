package com.example.movingapi.presentation.Screen.Profile


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.cachedIn
import com.example.movingapi.domain.Profile.GetSessionIdUseCase
import com.example.movingapi.domain.Profile.GetUseAccountUseCase
import com.example.movingapi.domain.Profile.GetUserTokenUseCase
import com.example.movingapi.model.UIState
import com.example.movingapi.model.UserAccount
import com.example.movingapi.model.UserTokenResponse

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val getSessionIdUseCase: GetSessionIdUseCase,
    private val getUserAccountUseCase: GetUseAccountUseCase
): ViewModel(){
    var userAccountState  : MutableStateFlow<UIState<UserAccount>> = MutableStateFlow(UIState.Loading())
    var userSessionState : MutableStateFlow<UIState<UserTokenResponse>> = MutableStateFlow(UIState.Loading())
    var userTokenState: MutableStateFlow<UIState<UserTokenResponse>> = MutableStateFlow(UIState.Loading())
    fun getUserToken(){
        viewModelScope.launch {
         when (val response = getUserTokenUseCase.invoke()) {
             is UIState.Success -> userTokenState.value = UIState.Success(response.data)
             is UIState.Error -> userTokenState.value = UIState.Error(response.error)
             is UIState.Empty -> userTokenState.value = UIState.Empty(title = response.title)
             is UIState.Loading -> userTokenState.value = UIState.Loading()
         }

         }
    }
    fun getSessionId(requestToken: String){ //هنا لازم اغير اسم الباراميتر
        viewModelScope.launch {
            when (val response = getSessionIdUseCase.invoke(requestToken)) {
                is UIState.Success -> userSessionState.value  = UIState.Success(response.data)
                is UIState.Error -> userSessionState.value = UIState.Error(response.error)
                is UIState.Empty -> userSessionState.value = UIState.Empty(title = response.title)
                is UIState.Loading -> userSessionState.value = UIState.Loading()
            }

        }
    }
    fun getUserAccount(sessionId: String){
        viewModelScope.launch {
            when (val response = getUserAccountUseCase.invoke(sessionId)) {
                is UIState.Success -> userAccountState.value  = UIState.Success(response.data)
                is UIState.Error -> userAccountState.value = UIState.Error(response.error)
                is UIState.Empty -> userAccountState.value = UIState.Empty(title = response.title)
                is UIState.Loading -> userAccountState.value = UIState.Loading()
            }

        }
    }
}
