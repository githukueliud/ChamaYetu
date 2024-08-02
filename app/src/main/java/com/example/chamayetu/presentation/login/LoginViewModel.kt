package com.example.chamayetu.presentation.login

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chamayetu.data.network.RetrofitInstance
import com.example.chamayetu.data.network.UserDto
import com.example.chamayetu.data.repository.AuthRepository
import com.example.chamayetu.data.repository.FormValidationRepository
import com.example.chamayetu.presentation.navigation.Destinations
import com.example.chamayetu.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    //private val authRepository: AuthRepository,
    private val formValidationRepository: FormValidationRepository,
    private val userRepository: UserRepository
): ViewModel() {

    private var _user = MutableStateFlow<UserDto?>(null)
    val user: StateFlow<UserDto?> get() = _user

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<LoginUIEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val loginApi = RetrofitInstance.loginApi


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




    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = loginApi.userLogin(loginRequest)
                if (request.isSuccessful) {
                    val user = request.body()?.userDto!!
                    val token = request.body()?.token // Get the token

                    if (token != null) {
                        // Set the token in RetrofitInstance
                        RetrofitInstance.setToken(token)
                    }
                    _eventFlow.emit(LoginUIEvents.NavigateToHome)
                } else {
                    _eventFlow.emit(LoginUIEvents.ShowSnackBar("Login failed"))
                }
            } catch (e: Exception) {
                _eventFlow.emit(LoginUIEvents.ShowSnackBar("Login failed"))
            }
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
                LoginRequest(_state.value.email, _state.value.password)
            )
        }
    }



}