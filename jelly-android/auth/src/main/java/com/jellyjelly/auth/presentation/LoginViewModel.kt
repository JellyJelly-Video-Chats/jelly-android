package com.jellyjelly.auth.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jellyjelly.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    private val _authState = MutableLiveData<LoginState>()
    val authState: LiveData<LoginState> = _authState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = LoginState.Loading

            // TODO: Implement actual authentication
            // For now, just simulate success after a delay
            kotlinx.coroutines.delay(1000)
            _authState.value = LoginState.Success
        }
    }
}

sealed class LoginState {
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
} 