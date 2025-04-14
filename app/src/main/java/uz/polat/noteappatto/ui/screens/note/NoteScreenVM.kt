package uz.polat.noteappatto.ui.screens.note

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.polat.noteappatto.data.repository.NoteRepository
import uz.polat.noteappatto.data.source.local.room.entity.NoteData
import uz.polat.noteappatto.data.source.local.room.entity.NoteData.*
import uz.polat.noteappatto.data.source.local.room.entity.NoteEntity
import uz.polat.noteappatto.data.source.local.room.entity.TopicEntity
import uz.polat.noteappatto.ui.screens.note.NoteScreenContracts.*
import uz.polat.noteappatto.utils.TOPIC_ADD
import javax.inject.Inject


@HiltViewModel
class NoteScreenVM @Inject constructor(
    private val noteRepository: NoteRepository,
    private val directions: NoteScreenContracts.Directions
) : NoteScreenContracts.ViewModel, ViewModel() {
    private lateinit var noteEntity: NoteEntity

    override fun onEventDispatcher(intent: NoteScreenContracts.Intent) = intent {
        when (intent) {
            is Intent.AddImage -> {
                val note = ArrayList(state.noteDatas)

                noteRepository.copyImageToAppStorage(intent.uri.toUri())
                    .catch { postSideEffect(SideEffect(it.message.toString())) }
                    .onEach {
                        val uri = it.toString()
                        note.add(Img(uri))
                        reduce { state.copy(noteDatas = note) }
                    }
                    .launchIn(viewModelScope)
            }

            is Intent.AddQuote -> {
                val note = ArrayList(state.noteDatas)
                note.add(QuotedText(""))

                reduce { state.copy(noteDatas = note) }
            }

            Intent.AddText -> {
                val note = ArrayList(state.noteDatas)
                note.add(Text(""))

                reduce { state.copy(noteDatas = note) }
            }

            is Intent.OnClickCancel -> {
                reduce { state.copy(showCancelDialog = true) }
            }

            Intent.ConfirmCancel -> {
                directions.goBack()
            }

            Intent.HideCancelDialog -> {
                reduce { state.copy(showCancelDialog = false) }
            }

            is Intent.OnClickSave -> {
                if (!::noteEntity.isInitialized) {

                    val noteEntity = NoteEntity(
                        noteDatas = state.noteDatas,
                        createdTime = System.currentTimeMillis(),
                        topicIds = state.selectedTopics.map { it.id },
                        colors = state.selectedTopics.map { it.color }
                    )

                    noteRepository.createNote(noteEntity)
                        .catch { postSideEffect(SideEffect(it.message.toString())) }
                        .onEach {
                            directions.goBack()
                        }.launchIn(viewModelScope)

                } else {

                    noteRepository.updateNote(noteEntity.copy(
                        noteDatas = state.noteDatas,
                        topicIds = state.selectedTopics.map { it.id },
                        colors = state.selectedTopics.map { it.color }
                        ))
                        .catch { postSideEffect(SideEffect(it.message.toString())) }
                        .onEach { directions.goBack() }
                        .launchIn(viewModelScope)

                }
            }

            Intent.OnClickRemoveLast -> {
                if (state.noteDatas.size == 1) {
                    postSideEffect(SideEffect("Title can note be deleted!"))
                } else {
                    reduce { state.copy(showRemoveItemDialog = true) }
                }
            }

            Intent.ConfirmDeleteLastItem -> {
                val updatedList = state.noteDatas.dropLast(1)
                reduce { state.copy(noteDatas = updatedList) }
            }

            Intent.HideRemoveItemConfirmDialog -> {
                reduce { state.copy(showRemoveItemDialog = false) }
            }

            Intent.ConfirmDeleteNote -> {
                noteRepository.deleteNote(noteEntity)
                    .catch { postSideEffect(SideEffect(it.message.toString())) }
                    .onEach { directions.goBack() }
                    .launchIn(viewModelScope)
            }

            Intent.DeleteNote -> {
                reduce { state.copy(showDeleteNoteDialog = true) }
            }

            Intent.HideDeleteNoteConfirmDialog -> {
                reduce { state.copy(showDeleteNoteDialog = false) }
            }

            Intent.OnClickAddCategory -> {
                reduce { state.copy(isAddTopicBottomSheetOpen = true) }
            }

            Intent.OnDismissClickBottomSheet -> {
                reduce { state.copy(isAddTopicBottomSheetOpen = false) }
            }

            is Intent.OnClickCategory -> {
                if (state.selectedTopics.contains(intent.topicEntity)) {
                    val updatedSelectedTopics = ArrayList(state.selectedTopics)
                    updatedSelectedTopics.remove(intent.topicEntity)
                    reduce { state.copy(selectedTopics = updatedSelectedTopics) }
                } else {
                    val updatedSelectedTopics = ArrayList(state.selectedTopics)
                    updatedSelectedTopics.add(intent.topicEntity)
                    reduce { state.copy(selectedTopics = updatedSelectedTopics) }
                }

            }

            is Intent.OnClickSaveTopic -> {
                noteRepository.createTopic(
                    TopicEntity(
                        name = intent.name,
                        color = intent.color.value.toLong()
                    )
                )
                    .catch { postSideEffect(NoteScreenContracts.SideEffect(it.message.toString())) }
                    .onEach {
//                        getTopics()
                        reduce { state.copy(isAddTopicBottomSheetOpen = false) }
                    }.launchIn(viewModelScope)
            }

        }
    }

    override fun init(noteEntity: NoteEntity?) {
        getAllAndSelectedTopics()
        if (noteEntity != null) {
            intent {
                reduce {
                    state.copy(
                        noteDatas = noteEntity.noteDatas,
                    )
                }
            }
            this.noteEntity = noteEntity
        } else {
            intent {
                val list = ArrayList<NoteData>()
                list.add(NoteData.Title(""))
                list.add(NoteData.Text(""))

                reduce { state.copy(noteDatas = list) }
            }

        }

    }

    private fun getAllAndSelectedTopics() = intent {
        noteRepository.getAllTopics()
            .catch { postSideEffect(NoteScreenContracts.SideEffect(it.message.toString())) }
            .onEach {
                val list = ArrayList(it)
                list.add(list.size, TopicEntity(id = -2, name = TOPIC_ADD, color = 0))
                reduce { state.copy(topics = list) }
            }.launchIn(viewModelScope)

        if(::noteEntity.isInitialized){
            noteRepository.getTopicsByIds(noteEntity.topicIds)
                .catch { postSideEffect(NoteScreenContracts.SideEffect(it.message.toString())) }
                .onEach {
                    reduce { state.copy(selectedTopics = it) }
                }.launchIn(viewModelScope)
        }

    }


//    private fun removeIfEmpty(note: ArrayList<NoteData>) {
//        when (val it = note.last()) {
//            is NoteData.Img -> {}
//            is NoteData.QuotedText -> {
//                if (it.quote.isEmpty())
//                    note.dropLast(1)
//            }
//
//            is NoteData.Text -> {
//                if (it.text.isEmpty())
//                    note.dropLast(1)
//            }
//
//            else -> {}
//        }
//    }


    override val container = container<NoteScreenContracts.UIState, NoteScreenContracts.SideEffect>(NoteScreenContracts.UIState())
}