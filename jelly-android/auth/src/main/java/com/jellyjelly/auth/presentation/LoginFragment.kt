package com.jellyjelly.auth.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jellyjelly.auth.databinding.FragmentLoginBinding
import com.jellyjelly.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSignIn.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            viewModel.signIn(email, password)
        }

        binding.buttonSignUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginToRegister())
        }

        // Observe auth state
        viewModel.authState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoginState.Success -> {
                    findNavController().navigate(LoginFragmentDirections.actionLoginToHome())
                }
                is LoginState.Error -> {
                    // Show error
                }
                is LoginState.Loading -> {
                    // Show loading
                }
            }
        }
    }
} 