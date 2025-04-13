package com.example.notesapp.ui.screen.main.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.notesapp.ui.utill.ADD_PAGE
import com.example.notesapp.ui.utill.HOME_PAGE
import com.example.notesapp.ui.utill.TOPIC_PAGE

sealed class BottomBarNavigation(val route: String, val icon: ImageVector) {
    object HomePage : BottomBarNavigation(
        route = HOME_PAGE, icon = Icons.Default.Home
    )

    object TopicPage : BottomBarNavigation(
        route = TOPIC_PAGE, icon = Icons.AutoMirrored.Filled.List
    )

    object AddPage : BottomBarNavigation(
        route = ADD_PAGE, icon = Icons.Default.Add
    )
}