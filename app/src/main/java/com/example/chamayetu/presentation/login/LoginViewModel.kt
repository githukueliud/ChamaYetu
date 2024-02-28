package com.example.chamayetu.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chamayetu.data.repository.AuthRepository
import com.example.chamayetu.data.repository.FormValidationRepository
import com.example.chamayetu.presentation.navigation.Destinations
import com.example.chamayetu.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val formValidationRepository: FormValidationRepository
): ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<LoginUIEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvents) {
        when(event) {
            is LoginEvents.OnEmailChanged -> {
                _state.update { it.copy(email = event.email) }
            }
            is LoginEvents.OnPasswordChanged -> {
                _state.update { it.copy(password = event.password) }
            }
            LoginEvents.OnLoginClicked -> {
                submitData()
            }
            LoginEvents.OnSignupClicked -> {
                viewModelScope.launch {
                    _eventFlow.emit(LoginUIEvents.NavigateToSignup)
                }
            } else -> {}
        }
    }


    private fun submitData() {

        val emailResult = formValidationRepository.validateEmail(_state.value.email)
        val passwordResult = formValidationRepository.validatePassword(_state.value.password)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { !it.successful }


        if (hasError) {
            _state.update {
                it.copy(
                    emailError = emailResult.message,
                    passwordError = passwordResult.message
                )
            }
            return
        } else {
            login(
                _state.value.email,
                _state.value.password
            )
        }
    }


    private fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.loginUser(email, password).collect {result ->
                when(result) {
                    is Resource.Error -> {
                        _state.update { it.copy(loading = false) }
                        _eventFlow.emit(LoginUIEvents.ShowSnackBar(result.message + "Error occurred when logging in"))
                    }
                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }
                    is Resource.Success -> {
                        _state.update { it.copy(loading = false) }
                        delay(3000)
                        _eventFlow.emit(LoginUIEvents.ShowSnackBar("Success"))
                        delay(1500)
                        _eventFlow.emit(LoginUIEvents.NavigateToHome)
                        Destinations.AppNavigation.route
                    } else -> {
                    _state.update { it.copy(loading = false) }
                    _eventFlow.emit(LoginUIEvents.ShowSnackBar(result.message + "Error occurred when login in"))
                }
                }
            }
        }
    }
}