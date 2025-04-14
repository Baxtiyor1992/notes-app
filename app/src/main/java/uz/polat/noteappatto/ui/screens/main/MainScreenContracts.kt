package uz.polat.noteappatto.ui.screens.main

import androidx.compose.ui.graphics.Color
import org.orbitmvi.orbit.ContainerHost
import uz.polat.noteappatto.data.source.local.room.entity.NoteEntity
import uz.polat.noteappatto.data.source.local.room.entity.TopicEntity

interface MainScreenContracts {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
        fun getNotesAndTopics()
    }

    data class SideEffect(val message: String)

    data class UIState(
        val initState: Int = 0,
        val isAddTopicSheetOpen: Boolean = false,
        val isSettingsSheetOpen: Boolean = false,
        val isDarkMode: Boolean = false,
        val searchText: String = "",
        val notes: List<NoteEntity> = emptyList(),
        val selectedTopic:Int = 0,
        val currentLang:String = "en",
//        val selectedTopics: List<TopicEntity> = emptyList(),
        val topics: List<TopicEntity> = emptyList()
    )

    sealed interface Intent {
        data object OnClickAddButton : Intent
        data class OnClickNoteItem(val noteEntity: NoteEntity) : Intent
        data class OnClickCategory(val topicEntity: TopicEntity,val index:Int) : Intent
        data class OnToggleThemeMode(val isDarkMode: Boolean) : Intent
        data class LangChange(val checked:Boolean) : Intent
        data class SearchTextChange(val searchText: String) : Intent
        object OnDismissSettingsBottomSheet: Intent
        object OnClickSettingsIcon: Intent
        object OnClickAddCategory: Intent
        object OnDismissTopicAddBottomSheet: Intent
        data class OnClickSaveTopic(val name: String,val color:Color): Intent
    }

    interface Directions {
        suspend fun moveToAddNoteScreen(noteEntity: NoteEntity?)
    }
}