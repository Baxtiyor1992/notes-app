package uz.polat.noteappatto.ui.screens.main

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
import timber.log.Timber
import uz.polat.noteappatto.data.repository.NoteRepository
import uz.polat.noteappatto.data.source.local.room.entity.TopicEntity
import uz.polat.noteappatto.data.source.local.sharedPref.LocalStorage
import uz.polat.noteappatto.ui.screens.main.MainScreenContracts.*
import uz.polat.noteappatto.ui.theme.changeAppToDarkMode
import uz.polat.noteappatto.ui.theme.isDarkMode
import uz.polat.noteappatto.utils.TOPIC_ADD
import uz.polat.noteappatto.utils.TOPIC_ALL
import javax.inject.Inject


@HiltViewModel
class MainScreenVM @Inject constructor(
    private val directions: MainScreenContracts.Directions,
    private val repository: NoteRepository,
    private val localStorage: LocalStorage
) : MainScreenContracts.ViewModel, ViewModel() {


    override fun onEventDispatcher(intent: MainScreenContracts.Intent) = intent {
        when (intent) {
            is Intent.OnClickAddButton -> {
                directions.moveToAddNoteScreen(null)
            }

            is Intent.OnClickNoteItem -> {
                directions.moveToAddNoteScreen(intent.noteEntity)
            }

            is Intent.OnClickCategory -> {
                if (intent.topicEntity.name == TOPIC_ALL) {
                    getNotes()
                }else{
                    repository.getNotesByTopic(intent.topicEntity)
                        .catch { postSideEffect(SideEffect(it.message.toString())) }
                        .onEach {
                            Timber.tag("TTT").d("getNotesByTopic ${intent.topicEntity.name}: $it")
                            reduce { state.copy(notes = it) }
                        }.launchIn(viewModelScope)
                }

            }

            Intent.OnClickAddCategory -> {
                reduce { state.copy(isAddTopicSheetOpen = true) }
            }

            Intent.OnDismissTopicAddBottomSheet -> {
                reduce { state.copy(isAddTopicSheetOpen = false) }
            }

            is Intent.OnClickSaveTopic -> {
                repository.createTopic(TopicEntity(
                    name = intent.name,
                    color = intent.color.value.toLong()
                ))
                    .catch { postSideEffect(SideEffect(it.message.toString())) }
                    .onEach {
//                        getTopics()
                        reduce { state.copy(isAddTopicSheetOpen = false) }
                    }.launchIn(viewModelScope)
            }

            Intent.OnDismissSettingsBottomSheet -> {
                reduce { state.copy(isSettingsSheetOpen = false) }
            }
            is Intent.OnToggleThemeMode -> {
                changeAppToDarkMode(intent.isDarkMode)
                localStorage.isDarkMode = intent.isDarkMode
                reduce { state.copy(isDarkMode = isDarkMode) }
            }

            Intent.OnClickSettingsIcon -> {
                reduce { state.copy(isSettingsSheetOpen = true) }
            }
        }
    }


    override fun getNotesAndTopics() {
        getNotes()
        getTopics()
    }

    private fun getNotes() = intent {
            repository.getAllNotes()
                .catch { postSideEffect(MainScreenContracts.SideEffect(it.message.toString())) }
                .onEach {
                    Timber.tag("TTT").d("getNotes: $it")
                    reduce { state.copy(notes = it) }
                }.launchIn(viewModelScope)

    }

    private fun getTopics() = intent {
            repository.getAllTopics()
                .catch { postSideEffect(MainScreenContracts.SideEffect(it.message.toString())) }
                .onEach {
                    val list = ArrayList(it)
                    list.add(0, TopicEntity(id = -1, name = TOPIC_ALL, color = 0))
                    list.add(list.size, TopicEntity(id = -2, name = TOPIC_ADD, color = 0))
                    reduce { state.copy(topics = list) }
                }.launchIn(viewModelScope)

    }

    override val container =
        container<MainScreenContracts.UIState, MainScreenContracts.SideEffect>(
            MainScreenContracts.UIState().copy(isDarkMode = localStorage.isDarkMode)
        )
}