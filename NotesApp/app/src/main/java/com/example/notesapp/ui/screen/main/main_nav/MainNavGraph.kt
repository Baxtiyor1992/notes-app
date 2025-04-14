package com.example.notesapp.ui.screen.main.main_nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.notesapp.ui.screen.main.SharedViewModel
import com.example.notesapp.ui.screen.main.detail.DetailScreen
import com.example.notesapp.ui.screen.main.home.HomeScreen
import com.example.notesapp.ui.screen.main.search.SearchScreen
import com.example.notesapp.ui.screen.main.set_up_topic.SetUpTopic
import com.example.notesapp.ui.screen.main.topic_notes.TopicNotesScreen
import com.example.notesapp.ui.utill.CHOOSE_TOPIC
import com.example.notesapp.ui.utill.DETAIL_SCREEN
import com.example.notesapp.ui.utill.MAIN_ROUTE
import com.example.notesapp.ui.utill.MAIN_SCREEN
import com.example.notesapp.ui.utill.SEARCH_SCREEN
import com.example.notesapp.ui.utill.TOPIC_NOTES

fun NavGraphBuilder.setUpMainNavigation(
    navHostController: NavHostController, sharedViewModel: SharedViewModel
) {
    navigation(
        startDestination = MAIN_SCREEN, route = MAIN_ROUTE
    ) {
        composable(route = MAIN_SCREEN) {
            HomeScreen(navHostController = navHostController, sharedViewModel = sharedViewModel)
        }
        composable(route = DETAIL_SCREEN) {
            DetailScreen(
                mainNavHostController = navHostController, sharedViewModel = sharedViewModel
            )
        }
        composable(route = SEARCH_SCREEN) { SearchScreen() }
        composable(route = CHOOSE_TOPIC) {
            SetUpTopic(
                mainNavHostController = navHostController, sharedViewModel = sharedViewModel
            )
        }
        composable(route = TOPIC_NOTES) {
            TopicNotesScreen(
                mainNavHostController = navHostController,
                sharedViewModel = sharedViewModel
            )
        }
    }
}