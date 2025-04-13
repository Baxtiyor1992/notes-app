package com.example.notesapp.ui.screen.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.notesapp.ui.utill.SPLASH_ROUTE
import com.example.notesapp.ui.utill.SPLASH_SCREEN

fun NavGraphBuilder.setUpSplashNavigation(navHostController: NavHostController) {
    navigation(
        startDestination = SPLASH_SCREEN,
        route = SPLASH_ROUTE
    ) {
        composable(
            route = SPLASH_SCREEN,
        ) {
            SplashScreen(navHostController = navHostController)
        }
    }
}