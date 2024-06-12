package com.example.chamayetu.presentation.signup

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chamayetu.data.model.MyUser
import com.example.chamayetu.data.network.ApiRepository
import com.example.chamayetu.data.repository.AuthRepository
import com.example.chamayetu.data.repository.DatabaseRepositoryImpl
import com.example.chamayetu.data.repository.FormValidationRepository
import com.example.chamayetu.presentation.login.LoginState
import com.example.chamayetu.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
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




    val myUser: MyUser =  MyUser(
        email = _state.value.email,
        password = _state.value.password,
        firstname = _state.value.firstName,
        username = _state.value.username,
        lastname = _state.value.lastName,
        phoneNumber = _state.value.phoneNumber
    )



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
        val usernameResult = formValidationRepository.validateUsername(_state.value.username)
        val emailResult = formValidationRepository.validateEmail(_state.value.email)
        val passwordResult = formValidationRepository.validatePassword(_state.value.password)
        val firstnameResult = formValidationRepository.validateFirstname(_state.value.firstName)
        val lastnameResult = formValidationRepository.validateLastname(_state.value.lastName)
        val phoneNumberResult = formValidationRepository.validatePhoneNumber(_state.value.phoneNumber)


        val hasError = listOf(
            usernameResult,
            emailResult,
            passwordResult,
            firstnameResult,
            lastnameResult,
            phoneNumberResult
        ).any{ !it.successful }


        if (hasError) {
            _state.update {
                it.copy(
                    usernameError = usernameResult.message,
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