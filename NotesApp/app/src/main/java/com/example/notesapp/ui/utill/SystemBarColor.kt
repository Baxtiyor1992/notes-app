package com.example.notesapp.ui.utill

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.notesapp.ui.theme.PrimaryColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemBarColorChanger(modifier: Modifier = Modifier) {
    val systemUiController = rememberSystemUiController()
    if (isSystemInDarkTheme()) {
        systemUiController.setStatusBarColor(color = PrimaryColor, darkIcons = false)
        systemUiController.setNavigationBarColor(color = PrimaryColor, darkIcons = false)
    }else{
        systemUiController.setStatusBarColor(color = PrimaryColor, darkIcons = false)
        systemUiController.setNavigationBarColor(color = PrimaryColor, darkIcons = false)
    }
}