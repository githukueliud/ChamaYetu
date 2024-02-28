package com.example.chamayetu.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.chamayetu.presentation.login.LoginScreen
import com.example.chamayetu.presentation.signup.SignupScreen
import com.example.chamayetu.presentation.bottomBar.AppNavigator


@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        //login and sign up process
        navigation(
            route = Destinations.AppAuthProcess.route,
            startDestination = Destinations.SignupScreen.route
        ) {
            composable(route = Destinations.SignupScreen.route) {
                SignupScreen(navController = navController)
            }
            composable(route = Destinations.LoginScreen.route) {
                LoginScreen(navController = navController)
            }
        }

        //app navigation once logged in
        navigation(
            startDestination = Destinations.AppNavigatorScreen.route,
            route = Destinations.AppNavigation.route
        ) {
            composable(route = Destinations.AppNavigatorScreen.route) {
                AppNavigator()
            }
        }
    }
}