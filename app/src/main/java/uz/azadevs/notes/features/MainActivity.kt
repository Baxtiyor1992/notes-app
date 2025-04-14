package uz.azadevs.notes.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import uz.azadevs.notes.common.ui.theme.NotesTheme
import uz.azadevs.notes.features.home.HomeScreen
import uz.azadevs.notes.features.utils.HomeScreen
import uz.azadevs.notes.features.utils.NotesScreen

class
MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NotesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = HomeScreen) {
                        composable<HomeScreen> {
                            HomeScreen(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                                onNavigate = {
                                    navController.navigate(NotesScreen(it))
                                }
                            )
                        }
                        composable<NotesScreen> {
                            val note: NotesScreen = it.toRoute()
                            uz.azadevs.notes.features.note.notes.NotesScreen(
                                noteId = note.noteId,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

