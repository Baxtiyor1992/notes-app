package com.example.notesapp.ui.screen.main.main_nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.notesapp.ui.screen.main.detail.DetailScreen
import com.example.notesapp.ui.screen.main.home.MainScreen
import com.example.notesapp.ui.screen.main.search.SearchScreen
import com.example.notesapp.ui.utill.DETAIL_SCREEN
import com.example.notesapp.ui.utill.MAIN_ROUTE
import com.example.notesapp.ui.utill.MAIN_SCREEN
import com.example.notesapp.ui.utill.SEARCH_SCREEN

fun NavGraphBuilder.setUpMainNavigation(navHostController: NavHostController) {
    navigation(
        startDestination = MAIN_SCREEN,
        route = MAIN_ROUTE
    ) {
        composable(route = MAIN_SCREEN) {
            MainScreen(navHostController = navHostController)
        }

        composable(route = DETAIL_SCREEN) { DetailScreen() }
        composable(route = SEARCH_SCREEN) { SearchScreen() }
    }
}