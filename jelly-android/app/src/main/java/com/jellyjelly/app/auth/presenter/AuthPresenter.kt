package com.jellyjelly.app.auth.presenter

import com.jellyjelly.app.auth.view.AuthView

/**
 * MVP Presenter for authentication
 */
class AuthPresenter(private val view: AuthView) {
    
    // Mock authentication for demo purposes
    fun login(username: String, password: String) {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            // In a real app, you would authenticate with a service
            view.onLoginSuccess()
        } else {
            view.onLoginError("Please enter both username and password")
        }
    }
    
    fun signup(email: String, username: String, password: String, confirmPassword: String) {
        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            view.onSignupError("All fields are required")
            return
        }
        
        if (password != confirmPassword) {
            view.onSignupError("Passwords do not match")
            return
        }
        
        // In a real app, you would create an account with a service
        view.onSignupSuccess()
    }
} 