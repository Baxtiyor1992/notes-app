package uz.polat.noteappatto.ui.screens.note

import androidx.compose.ui.graphics.Color
import org.orbitmvi.orbit.ContainerHost
import uz.polat.noteappatto.data.source.local.room.entity.NoteData
import uz.polat.noteappatto.data.source.local.room.entity.NoteEntity
import uz.polat.noteappatto.data.source.local.room.entity.TopicEntity
import uz.polat.noteappatto.ui.screens.main.MainScreenContracts


interface NoteScreenContracts {

    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
        fun init(noteEntity: NoteEntity?)
    }

    data class SideEffect(
        val message: String = ""
    )

    data class UIState(
        val initState: Int = 0,
        val noteDatas: List<NoteData> = emptyList(),
        val topics: List<TopicEntity> = emptyList(),
        val selectedTopics: List<TopicEntity> = emptyList(),
        var isAddTopicBottomSheetOpen: Boolean = false,
        var showRemoveItemDialog: Boolean = false,
        var showDeleteNoteDialog: Boolean = false,
        var showCancelDialog: Boolean = false
    )

    sealed interface Intent {
        data object OnClickSave : Intent
        data object OnClickCancel : Intent
        data object ConfirmCancel : Intent
        data object HideCancelDialog : Intent

        data object AddQuote : Intent
        data object AddText : Intent
        data class AddImage(val uri: String) : Intent

        data object OnClickRemoveLast : Intent
        data object HideRemoveItemConfirmDialog : Intent
        data object ConfirmDeleteLastItem : Intent


        object OnClickAddCategory: Intent
        data class OnClickCategory(val topicEntity: TopicEntity): Intent
        object OnDismissClickBottomSheet: Intent
        data class OnClickSaveTopic(val name: String,val color:Color): Intent

        data object DeleteNote : Intent
        data object ConfirmDeleteNote : Intent
        data object HideDeleteNoteConfirmDialog : Intent


    }


    interface Directions {
        suspend fun goBack()
    }

}