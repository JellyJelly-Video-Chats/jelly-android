package com.jellyjelly.app.auth.view

/**
 * MVP View interface for authentication
 */
interface AuthView {
    fun onLoginSuccess()
    fun onLoginError(message: String)
    fun onSignupSuccess()
    fun onSignupError(message: String)
} 