package com.jellyjelly.auth.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.jellyjelly.auth.databinding.FragmentLoginBinding
import com.jellyjelly.common.base.BaseFragment
import com.jellyjelly.common.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun getViewBinding() = FragmentLoginBinding.inflate(layoutInflater)

    override fun setupViews() {
        with(binding) {
            emailInput.doAfterTextChanged { viewModel.onEmailChanged(it?.toString() ?: "") }
            passwordInput.doAfterTextChanged { viewModel.onPasswordChanged(it?.toString() ?: "") }
            loginButton.setOnClickListener { viewModel.onLoginClick() }
            signUpButton.setOnClickListener { viewModel.onSignUpClick() }
            forgotPasswordButton.setOnClickListener { viewModel.onForgotPasswordClick() }
        }
    }

    override fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.viewState.collectLatest { state ->
                updateUI(state)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.event.collectLatest { event ->
                handleEvent(event)
            }
        }
    }

    private fun updateUI(state: LoginState) {
        with(binding) {
            loginButton.isEnabled = !state.isLoading
            progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
            errorText.text = state.error
            errorText.visibility = if (state.error != null) View.VISIBLE else View.GONE
        }
    }

    private fun handleEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.ShowError -> showToast(event.message)
            LoginEvent.NavigateToHome -> {
                // TODO: Navigate to home screen
            }
            LoginEvent.NavigateToSignUp -> {
                // TODO: Navigate to sign up screen
            }
            LoginEvent.NavigateToForgotPassword -> {
                // TODO: Navigate to forgot password screen
            }
        }
    }
} 