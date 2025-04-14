package uz.polat.noteappatto

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.polat.noteappatto.data.repository.NoteRepository
import uz.polat.noteappatto.data.source.local.sharedPref.LocalStorage
import uz.polat.noteappatto.ui.screens.main.MainScreen
import uz.polat.noteappatto.ui.screens.onboarding.OnBoardingScreen
import uz.polat.noteappatto.ui.theme.NoteAppAttoTheme
import uz.polat.noteappatto.ui.theme.changeAppToDarkMode
import uz.polat.noteappatto.utils.navigation.NavigationHandler
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var navigationHandler: NavigationHandler

    @Inject
    lateinit var repository: NoteRepository
    @Inject
    lateinit var localStorage: LocalStorage

    @OptIn(ExperimentalVoyagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        changeAppToDarkMode(localStorage.isDarkMode)

        setContent {
            NoteAppAttoTheme {
                Navigator(
                    screen = if (repository.isFirstLaunch()) OnBoardingScreen() else MainScreen(),
                    onBackPressed = { true }) { navigator ->
                    LaunchedEffect(key1 = navigator) {
                        navigationHandler.navigationStack
                            .onEach {
                                it.invoke(navigator)
                            }
                            .launchIn(lifecycleScope)
                    }
                    SlideTransition(
                        navigator = navigator,
                        disposeScreenAfterTransitionEnd = true
                    )
                }
            }
        }
    }
}

