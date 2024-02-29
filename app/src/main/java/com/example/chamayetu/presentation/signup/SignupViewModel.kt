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
    private val authRepository: AuthRepository,
    private val application: Application
): AndroidViewModel(application = application) {
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


    private fun registerUser(email: String, password: String) {
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



     private fun addUserDetails() {

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbUsers: CollectionReference = db.collection("Users")

        val users = com.example.chamayetu.data.local.user.User()

         users.email = _state.value.email
         users.username = _state.value.username
         users.userId = _state.value.username
        dbUsers.add(users).addOnSuccessListener {
            Toast.makeText(application, "User added successfully!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e ->
            Toast.makeText(application, "Exception: $e", Toast.LENGTH_SHORT).show()
        }
    }








}