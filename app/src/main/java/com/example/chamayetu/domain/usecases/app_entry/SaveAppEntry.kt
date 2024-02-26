package com.example.chamayetu.domain.usecases.app_entry

import com.example.chamayetu.domain.manager.LocalUserManager

class SaveAppEntry (
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        return localUserManager.saveAppEntry()
    }
}