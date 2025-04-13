package com.example.notesapp.ui.screens.main.main_nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.notesapp.ui.utill.DETAIL_SCREEN
import com.example.notesapp.ui.utill.MAIN_ROUTE
import com.example.notesapp.ui.utill.MAIN_SCREEN
import com.example.notesapp.ui.screens.main.detail.DetailScreen
import com.example.notesapp.ui.screens.main.home.MainScreen

fun NavGraphBuilder.setUpMainNavigation(navHostController: NavHostController) {
    navigation(
        startDestination = MAIN_SCREEN,
        route = MAIN_ROUTE
    ) {
        composable(route = MAIN_SCREEN) {
            MainScreen(navHostController = navHostController)
        }

        composable(route = DETAIL_SCREEN) { DetailScreen() }
    }
}