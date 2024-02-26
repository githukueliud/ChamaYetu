package com.example.chamayetu.presentation.navigation

sealed class Destinations (val route: String) {
    object LoginScreen: Destinations("loginScreen")
    object SignupScreen: Destinations("signupScreen")
    object FinesScreen: Destinations("finesScreen")
    object HomeScreen: Destinations("homeScreen")
    object LoansScreen: Destinations("loansScreen")
    object NewsScreen: Destinations("newsScreen")
    object SavingsScreen: Destinations("savingsScreen")
    object WelfareScreen: Destinations("welfareScreen")
    object AppAuthProcess: Destinations("appAuthProcess")

    object AppNavigation: Destinations("appNavigation")
    object AppNavigatorScreen: Destinations("App_Navigator_Screen")
    object AppStartNavigation: Destinations("appStartNavigation")
}