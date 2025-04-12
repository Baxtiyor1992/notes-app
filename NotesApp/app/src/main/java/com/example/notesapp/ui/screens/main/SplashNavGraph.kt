package com.example.notesapp.ui.screens.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.notesapp.ui.screens.constants.MAIN_ROUTE
import com.example.notesapp.ui.screens.constants.MAIN_SCREEN

fun NavGraphBuilder.setUpMainNavigation(navHostController: NavHostController) {
    navigation(
        startDestination = MAIN_SCREEN,
        route = MAIN_ROUTE
    ) {
        composable(route = MAIN_SCREEN) {
            MainScreen()
        }
    }
}