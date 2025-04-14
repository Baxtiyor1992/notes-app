package uz.polat.noteappatto.ui.screens.onboarding

import uz.polat.noteappatto.ui.screens.main.MainScreen
import uz.polat.noteappatto.utils.navigation.AppNavigator
import javax.inject.Inject

class OnBoardingDirections @Inject constructor(private val navigator: AppNavigator) : OnBoardingContracts.Directions {
    override suspend fun moveToMainScreen() {
        navigator.replace(MainScreen())
    }
}