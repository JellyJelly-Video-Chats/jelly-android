package com.jellyjelly.app.auth.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.jellyjelly.app.R
import com.jellyjelly.app.auth.presenter.AuthPresenter
import com.jellyjelly.app.auth.view.AuthView
import com.jellyjelly.app.databinding.DialogLoginBinding

/**
 * Login dialog following MVP pattern
 */
class LoginDialog(context: Context) : Dialog(context), AuthView {
    
    private lateinit var binding: DialogLoginBinding
    private val presenter = AuthPresenter(this)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Make dialog background transparent
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()
            presenter.login(username, password)
        }
        
        binding.textViewSignUp.setOnClickListener {
            dismiss()
            SignupDialog(context).show()
        }
    }
    
    override fun onLoginSuccess() {
        Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
        dismiss()
    }
    
    override fun onLoginError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    
    // Unused methods from interface
    override fun onSignupSuccess() {}
    override fun onSignupError(message: String) {}
} 