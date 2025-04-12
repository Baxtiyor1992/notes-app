package com.example.notesapp.ui.screens.systembar_color

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.notesapp.ui.theme.PrimaryColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemBarColorChanger(modifier: Modifier = Modifier) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = PrimaryColor, darkIcons = false)
    systemUiController.setNavigationBarColor(color = PrimaryColor, darkIcons = false)
}