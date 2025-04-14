package uz.polat.noteappatto.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import uz.polat.noteappatto.data.source.local.sharedPref.LocalStorage
import javax.inject.Inject

@HiltViewModel
class OnBoardingVM @Inject constructor(
    private val directions: OnBoardingContracts.Directions,
    private val localStorage: LocalStorage
) : OnBoardingContracts.ViewModel, ViewModel() {

    override fun onEventDispatcher(intent: OnBoardingContracts.Intent) {
        viewModelScope.launch {
            localStorage.isFirstLaunch = false
            directions.moveToMainScreen()
        }
    }

    override val container = container<OnBoardingContracts.UIState, Nothing>(OnBoardingContracts.UIState())
}