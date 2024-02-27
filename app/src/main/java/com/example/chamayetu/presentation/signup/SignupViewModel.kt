package com.example.chamayetu.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chamayetu.data.repository.AuthRepository
import com.example.chamayetu.data.repository.FormValidationRepository
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
class SignupViewModel @Inject constructor(
    private val formValidationRepository: FormValidationRepository,
    private val authRepository: AuthRepository
): ViewModel() {
    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<SignupUiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(event: SignupEvents) {
        when(event) {
            is SignupEvents.OnEmailChanged -> {
                _state.update { it.copy(email = event.email) }
            }

            is SignupEvents.OnPasswordChanged -> {
                _state.update { it.copy(password = event.password) }
            }
            is SignupEvents.OnUsernameChanged -> {
                _state.update { it.copy(username = event.name) }
            }
            SignupEvents.OnSignupClicked -> {
                submitData()
            }
            SignupEvents.OnLoginClicked -> {
                viewModelScope.launch {
                    _eventFlow.emit(SignupUiEvents.NavigateToLogin)
                }
            }
        }
    }




    private fun submitData() {
        val usernameResult = formValidationRepository.validateUsername(_state.value.username)
        val emailResult = formValidationRepository.validateEmail(_state.value.email)
        val passwordResult = formValidationRepository.validatePassword(_state.value.password)

        val hasError = listOf(
            usernameResult,
            emailResult,
            passwordResult
        ).any{ !it.successful }


        if (hasError) {
            _state.update {
                it.copy(
                    usernameError = usernameResult.message,
                    emailError = emailResult.message,
                    passwordError = passwordResult.message
                )
            }
            return
        } else {
            //handle the sign up request
            registerUser(
                _state.value.email,
                _state.value.password
            )

        }

    }


    fun registerUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000)
            authRepository.registerUser(email, password).collect{result ->
                when(result) {
                    is Resource.Success -> {
                        delay(3000)
                        _state.update { it.copy(loading = false) }
                        _eventFlow.emit(SignupUiEvents.ShowSnackBar("Account Created"))
                        delay(1500)
                        _eventFlow.emit(SignupUiEvents.NavigateToLogin)
                    }

                    is Resource.Error -> {
                        _state.update { it.copy(loading = false) }
                        _eventFlow.emit(SignupUiEvents.ShowSnackBar("An error occurred"))
                    }

                    is Resource.Loading -> {
                        _state.update { it.copy(loading = true) }
                    }
                }
            }
        }
    }

}