package uz.polat.noteappatto.ui.screens.onboarding

import org.orbitmvi.orbit.ContainerHost

interface OnBoardingContracts {
    interface ViewModel : ContainerHost<UIState, Nothing> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(val initState: Int = 0)

    sealed interface Intent {
        data object OnClickStart : Intent
    }

    interface Directions {
        suspend fun moveToMainScreen()
    }
}