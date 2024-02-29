package com.example.chamayetu.data.repository

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class DatabaseRepositoryImpl: DatabaseRepository {
    @SuppressLint("RestrictedApi")
    override fun addUserDetails( application: Application) {

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val dbUsers: CollectionReference = db.collection("Users")

        val users = com.example.chamayetu.data.local.user.User()

        dbUsers.add(users).addOnSuccessListener {
            Toast.makeText(application, "User added successfully!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e ->
            Toast.makeText(application, "Exception: $e", Toast.LENGTH_SHORT).show()
        }
    }
}