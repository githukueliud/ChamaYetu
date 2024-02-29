package com.example.chamayetu.data.repository

import android.app.Application

interface DatabaseRepository {
    fun addUserDetails(application: Application)
}