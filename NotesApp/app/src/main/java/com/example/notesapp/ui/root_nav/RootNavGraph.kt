package com.example.notesapp.ui.root_nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.notesapp.ui.utill.SPLASH_ROUTE
import com.example.notesapp.ui.screen.main.main_nav.setUpMainNavigation
import com.example.notesapp.ui.screen.splash.setUpSplashNavigation


@Composable
fun SetUpNavigation(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = SPLASH_ROUTE,
    ) {
        setUpSplashNavigation(navHostController = navHostController)
        setUpMainNavigation(navHostController = navHostController)
    }
}