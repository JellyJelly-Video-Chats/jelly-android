package com.jellyjelly.auth.presentation.login

import com.jellyjelly.auth.domain.usecase.SignInUseCase
import com.jellyjelly.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel<LoginState, LoginEvent>() {

    override fun createInitialState(): LoginState = LoginState()

    fun onEmailChanged(email: String) {
        setState { copy(email = email) }
    }

    fun onPasswordChanged(password: String) {
        setState { copy(password = password) }
    }

    fun onLoginClick() {
        val currentState = currentState
        if (currentState.isLoading) return

        setState { copy(isLoading = true, error = null) }

        viewModelScope.launch {
            signInUseCase(currentState.email, currentState.password)
                .onSuccess {
                    setState { copy(isLoading = false) }
                    setEvent(LoginEvent.NavigateToHome)
                }
                .onFailure { error ->
                    setState { copy(isLoading = false, error = error.message) }
                    setEvent(LoginEvent.ShowError(error.message ?: "Unknown error occurred"))
                }
        }
    }

    fun onSignUpClick() {
        setEvent(LoginEvent.NavigateToSignUp)
    }

    fun onForgotPasswordClick() {
        setEvent(LoginEvent.NavigateToForgotPassword)
    }
} 