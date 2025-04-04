package com.jellyjelly.app.auth.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.jellyjelly.app.R
import com.jellyjelly.app.auth.presenter.AuthPresenter
import com.jellyjelly.app.auth.view.AuthView
import com.jellyjelly.app.databinding.DialogSignupBinding

/**
 * Signup dialog following MVP pattern
 */
class SignupDialog(context: Context) : Dialog(context), AuthView {
    
    private lateinit var binding: DialogSignupBinding
    private val presenter = AuthPresenter(this)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Make dialog background transparent
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        binding.buttonSignUp.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            val confirmPassword = binding.editTextConfirmPassword.text.toString()
            
            presenter.signup(email, username, password, confirmPassword)
        }
        
        binding.textViewLogin.setOnClickListener {
            dismiss()
            LoginDialog(context).show()
        }
    }
    
    override fun onSignupSuccess() {
        Toast.makeText(context, "Signup successful", Toast.LENGTH_SHORT).show()
        dismiss()
    }
    
    override fun onSignupError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    
    // Unused methods from interface
    override fun onLoginSuccess() {}
    override fun onLoginError(message: String) {}
} 