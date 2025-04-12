package com.example.notesapp.ui.screens.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.notesapp.ui.screens.constants.DETAIL_ROUTE
import com.example.notesapp.ui.screens.constants.DETAIL_SCREEN

fun NavGraphBuilder.setUpDetailNavigation(navHostController: NavHostController) {
    navigation(
        startDestination = DETAIL_SCREEN,
        route = DETAIL_ROUTE
    ) {
        composable(route = DETAIL_SCREEN) {
            DetailScreen()
        }
    }
}