package uz.polat.noteappatto.ui.screens.main


import uz.polat.noteappatto.ui.screens.note.NoteScreen
import uz.polat.noteappatto.data.source.local.room.entity.NoteEntity
import uz.polat.noteappatto.utils.navigation.AppNavigator
import javax.inject.Inject

class MainScreenDirections @Inject constructor(
    private val appNavigator: AppNavigator
) : MainScreenContracts.Directions {
    override suspend fun moveToAddNoteScreen(noteEntity: NoteEntity?) {
        appNavigator.push(NoteScreen(noteEntity))
    }
}