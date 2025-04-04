package com.jellyjelly.auth.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed class LoginEvent {
    data class ShowError(val message: String) : LoginEvent()
    object NavigateToHome : LoginEvent()
    object NavigateToSignUp : LoginEvent()
    object NavigateToForgotPassword : LoginEvent()
} 