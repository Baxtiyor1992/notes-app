package uz.polat.noteappatto.ui.screens.note

import uz.polat.noteappatto.utils.navigation.AppNavigator
import javax.inject.Inject

class NoteScreenDirections @Inject constructor(
    private val appNavigator: AppNavigator
) : NoteScreenContracts.Directions {
    override suspend fun goBack() {
        appNavigator.back()
    }
}