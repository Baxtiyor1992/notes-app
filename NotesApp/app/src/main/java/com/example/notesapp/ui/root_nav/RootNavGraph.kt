package com.example.notesapp.ui.root_nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.notesapp.ui.screen.main.SharedViewModel
import com.example.notesapp.ui.screen.main.main_nav.setUpMainNavigation
import com.example.notesapp.ui.screen.splash.setUpSplashNavigation
import com.example.notesapp.ui.utill.SPLASH_ROUTE


@Composable
fun SetUpNavigation(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = SPLASH_ROUTE,
    ) {
        setUpSplashNavigation(navHostController = navHostController)
        setUpMainNavigation(
            navHostController = navHostController,
            sharedViewModel = sharedViewModel
        )
    }
}