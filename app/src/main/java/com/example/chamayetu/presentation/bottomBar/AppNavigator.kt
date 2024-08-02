package com.example.chamayetu.presentation.bottomBar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.chamayetu.R
import com.example.chamayetu.presentation.home.HomeScreen
import com.example.chamayetu.presentation.bottomBar.bottomBarComponent.BottomNavigation
import com.example.chamayetu.presentation.bottomBar.bottomBarComponent.BottomNavigationItem
import com.example.chamayetu.presentation.navigation.Destinations
import com.example.chamayetu.presentation.projects.ProjectsScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigator() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, "Home"),
            BottomNavigationItem(icon = R.drawable.ic_home, "Projects"),
            BottomNavigationItem(icon = R.drawable.ic_home, "News"),
            BottomNavigationItem(icon = R.drawable.ic_home, "Profile")
        )
    }


    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value

    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = when(backstackState?.destination?.route) {
        Destinations.HomeScreen.route -> 0
        Destinations.ProjectsScreen.route -> 1
        Destinations.HomeScreen.route -> 2
        Destinations.HomeScreen.route -> 3
        else -> 0
    }


    Scaffold(
        bottomBar = {
            BottomNavigation(
                items = bottomNavigationItems,
                selected = selectedItem,
                onItemClicked = {index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Destinations.HomeScreen.route
                        )
                        1 -> navigateToTab(
                            navController = navController,
                            route = Destinations.ProjectsScreen.route
                        )
                        2 -> navigateToTab(
                            navController = navController,
                            Destinations.HomeScreen.route
                        )
                        3 -> navigateToTab(
                            navController = navController,
                            route = Destinations.HomeScreen.route
                        )
                    }
                }
            )
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Destinations.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Destinations.HomeScreen.route) {
                HomeScreen()
            }
            composable(route = Destinations.ProjectsScreen.route) {
                ProjectsScreen()
            }
            composable(route = Destinations.HomeScreen.route) {
                HomeScreen()
            }
            composable(route = Destinations.HomeScreen.route) {
                HomeScreen()
            }
        }
    }



}


private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        restoreState = true
        launchSingleTop = true

    }
}
