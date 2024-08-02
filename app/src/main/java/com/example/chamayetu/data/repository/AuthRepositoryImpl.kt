package com.example.chamayetu.data.repository

import com.example.chamayetu.data.model.MyUser
import com.example.chamayetu.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

//class AuthRepositoryImpl @Inject constructor(
//    //private val firebaseAuth: FirebaseAuth
//): AuthRepository {
//
//    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
//        return flow {
//            emit(Resource.Loading())
//            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
//            emit(Resource.Success(result))
//        }.catch {
//            emit(Resource.Error(it.message.toString()))
//        }
//    }

//    override fun registerUser(
//        username: String,
//        firstname: String,
//        lastname: String,
//        email: String,
//        password: String,
//        phoneNumber: String,
//        role: String
//    ): Flow<Resource<MyUser>> {
//        TODO("Not yet implemented")
//    }

//    override fun registerUser(
//        username: String,
//        firstname: String,
//        lastname: String,
//        email: String,
//        password: String,
//        phoneNumber: String,
//        role: String
//    ): Flow<Resource<MyUser>> {
////        return flow {
////            emit(Resource.Loading())
//////            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
//////            emit(Resource.Success(result))
////        }.catch {
////            emit(Resource.Error(it.message.toString()))
////        }
//        return
//    }



//}