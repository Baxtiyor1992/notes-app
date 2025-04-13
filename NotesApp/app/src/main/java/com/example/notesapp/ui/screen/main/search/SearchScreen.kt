package com.example.notesapp.ui.screen.main.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.notesapp.ui.theme.PrimaryColor

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(color = PrimaryColor))
}