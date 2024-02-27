package com.example.chamayetu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chamayetu.domain.usecases.app_entry.AppEntryUseCases
import com.example.chamayetu.presentation.navigation.Destinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {
    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Destinations.AppAuthProcess.route)
        private set


    init {
        appEntryUseCases.readAppEntry().onEach {shouldStartFromSignupScreen ->
            if (shouldStartFromSignupScreen) {
                startDestination = Destinations.AppAuthProcess.route
            } else {
                startDestination = Destinations.AppAuthProcess.route
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}