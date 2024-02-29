package com.example.chamayetu.presentation.signup

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.chamayetu.data.local.user.User
import com.example.chamayetu.data.repository.DatabaseRepositoryImpl
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


//@HiltViewModel
//class AddNewUserViewModel @Inject constructor(
//    private val application: Application,
//    private val dataBaseRepository: DatabaseRepositoryImpl
//) : ViewModel() {
//
//
//
//
//}


//@HiltViewModel
//class AddNewUserViewModel @Inject constructor(private val application: Application): AndroidViewModel(application = application) {
//
//    //val repository = PhotifyModule.providesDatabaseRepositoryImpl()
//
//    var userName by  mutableStateOf("")
//
//    var userAge by mutableStateOf("")
//
//    var userOccupation by mutableStateOf("")
//
//    fun addUserDetails() {
//        val dB: FirebaseFirestore = FirebaseFirestore.getInstance()
//        val dbUsers: CollectionReference = dB.collection("Users")
//
//        val users = User()
//
//        dbUsers.add(users).addOnSuccessListener {
//            Toast.makeText(application, "User added successfully!", Toast.LENGTH_SHORT).show()
//        }.addOnFailureListener { e ->
//            Toast.makeText(application, "Exception: $e", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//}