package com.example.chamayetu.presentation.signup

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.chamayetu.data.model.MyUser
import com.example.chamayetu.data.network.ApiRepository
import com.example.chamayetu.data.network.RetrofitInstance
import com.example.chamayetu.data.network.SignupResponse
import com.example.chamayetu.data.repository.FormValidationRepository
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
    private val apiRepository: ApiRepository,
    private val application: Application
): AndroidViewModel(application = application) {

    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<SignupUiEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val signupApi = RetrofitInstance.signupApi




    private val myUser: MyUser =  MyUser(
        email = _state.value.email,
        password = _state.value.password,
        firstname = _state.value.firstName,
        lastname = _state.value.lastName,
        phoneNumber = _state.value.phoneNumber
    )

    fun signup(myUser: MyUser) {
        viewModelScope.launch {
            val request = signupApi.signupUser(myUser)
            try{
                if (request.isSuccessful) {
                    //Log.i("signup", request.body().toString())
                    _eventFlow.emit(SignupUiEvents.NavigateToLogin)
                } else {
                    //Log.i("signup: ", request.message())
                    _eventFlow.emit(SignupUiEvents.ShowSnackBar("Sign up failed"))
                }
            } catch (e: Exception) {
                _eventFlow.emit(SignupUiEvents.ShowSnackBar("Sign up failed"))
            }
        }
    }



    fun onEvent(event: SignupEvents) {
        when(event) {
            is SignupEvents.OnEmailChanged -> {
                _state.update { it.copy(email = event.email) }
            }

            is SignupEvents.OnPasswordChanged -> {
                _state.update { it.copy(password = event.password) }
            }
            is SignupEvents.OnFirstNameChanged -> {
                _state.update { it.copy(firstName = event.firstName) }
            }
            is SignupEvents.OnLastNameChanged -> {
                _state.update { it.copy(lastName = event.lastName) }
            }
            is SignupEvents.OnPhoneNumberChanged -> {
                _state.update { it.copy(phoneNumber = event.phoneNumber) }
            }
            SignupEvents.OnSignupClicked -> {
                submitData()
                addUserDetails()
            }
            SignupEvents.OnLoginClicked -> {
                viewModelScope.launch {
                    _eventFlow.emit(SignupUiEvents.NavigateToLogin)
                }
            }
        }
    }




    private fun submitData() {
        val emailResult = formValidationRepository.validateEmail(_state.value.email)
        val passwordResult = formValidationRepository.validatePassword(_state.value.password)
        val firstnameResult = formValidationRepository.validateFirstname(_state.value.firstName)
        val lastnameResult = formValidationRepository.validateLastname(_state.value.lastName)
        val phoneNumberResult = formValidationRepository.validatePhoneNumber(_state.value.phoneNumber)


        val hasError = listOf(
            emailResult,
            passwordResult,
            firstnameResult,
            lastnameResult,
            phoneNumberResult
        ).any{ !it.successful }


        if (hasError) {
            _state.update {
                it.copy(
                    emailError = emailResult.message,
                    passwordError = passwordResult.message,
                    phoneNumberError = phoneNumberResult.message,
                    lastNameError = lastnameResult.message,
                    firstNameError = firstnameResult.message
                )
            }
            return
        } else {
            //handle the sign up request
            registerUser(
                myUser
            )

        }

    }


    private fun registerUser(
        myUser: MyUser
    ) {
        viewModelScope.launch(Dispatchers.IO) {

            delay(3000)
            apiRepository.registerUser(myUser)
        }
    }



     private fun addUserDetails() {



    }








}