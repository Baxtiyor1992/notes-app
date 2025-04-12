package com.example.notesapp.ui.screens.nav_graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.notesapp.ui.screens.constants.SPLASH_ROUTE
import com.example.notesapp.ui.screens.detail.setUpDetailNavigation
import com.example.notesapp.ui.screens.main.setUpMainNavigation
import com.example.notesapp.ui.screens.splash.setUpSplashNavigation


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
        setUpDetailNavigation(navHostController = navHostController)
    }
}