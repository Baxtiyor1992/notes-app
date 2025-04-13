package com.example.notesapp.ui.screens.main.home.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController
) {
    Box(
        modifier
            .fillMaxSize()
            .background(color = Color.Green)
    )
}