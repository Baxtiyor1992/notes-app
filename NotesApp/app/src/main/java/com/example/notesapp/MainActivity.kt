package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.ui.root_nav.SetUpNavigation
import com.example.notesapp.ui.utill.SystemBarColorChanger
import com.example.notesapp.ui.theme.NotesAppTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                val navController = rememberNavController()
                SetUpNavigation(
                    navHostController = navController
                )
                SystemBarColorChanger()
            }
        }
    }
}
