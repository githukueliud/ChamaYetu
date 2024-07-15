package com.example.chamayetu.presentation.home

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.chamayetu.data.local.user.User
import com.example.chamayetu.data.network.UserDto
import com.example.chamayetu.presentation.login.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val application: Application,
    private val userRepository: UserRepository
) : AndroidViewModel(application = application){
    val user: Flow<UserDto> = userRepository.user


    val db = FirebaseFirestore.getInstance()

    val state = mutableStateOf(User())


    init {
        getData()
    }


    private fun getData() {
        viewModelScope.launch {
            state.value = getUserData()
        }
    }

    suspend fun getUserData(): com.example.chamayetu.data.local.user.User {
        val db = FirebaseFirestore.getInstance()
        var user = com.example.chamayetu.data.local.user.User()

        try {
            db.collection("Users").get().await().map {
                val result =  it.toObject(com.example.chamayetu.data.local.user.User::class.java)
                user = result
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "error getting user data")
        }


        return user
    }


}