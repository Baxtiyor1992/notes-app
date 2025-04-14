package uz.azadevs.notes.features.note.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.Note
import uz.azadevs.notes.domain.model.Topic
import uz.azadevs.notes.domain.usecase.note.add_update.AddUpdateNoteUseCase
import uz.azadevs.notes.domain.usecase.note.get_note.GetNoteByIdUseCase
import uz.azadevs.notes.domain.usecase.topic.topics.GetTopicsUseCase

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
class EditNoteViewModel(
    private val addUpdateNoteUseCase: AddUpdateNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val getAllTopicsUseCase: GetTopicsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(NoteState())
    val state = _state.asStateFlow()

    fun updateNote(note: Note) {
        _state.value = NoteState(isLoading = true)
        viewModelScope.launch {
            try {
                addUpdateNoteUseCase.invoke(note)
                _state.value = NoteState(note = note)
            } catch (e: Exception) {
                _state.value = NoteState(error = e.message)
            }
        }
    }

    fun getNoteById(id: Int) {
        _state.value = NoteState(isLoading = true)
        viewModelScope.launch {
            try {
                getNoteByIdUseCase.invoke(id).collect { note ->
                    when (note) {
                        is Result.Failure -> {
                            _state.value = NoteState(error = note.message)
                        }

                        is Result.Success -> {
                            _state.value = NoteState(note = note.data)
                        }
                    }

                }
            } catch (e: Exception) {
                _state.value = NoteState(error = e.message)
            }
        }
    }

    fun getTopics() {
        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                getAllTopicsUseCase().collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _state.value =
                                _state.value.copy(topics = result.data, isLoading = false)
                        }

                        is Result.Failure -> {
                            _state.value =
                                _state.value.copy(error = result.message, isLoading = false)
                        }
                    }
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(error = e.message, isLoading = false)
            }
        }
    }
}

data class NoteState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val note: Note? = null,
    val topics: List<Topic> = emptyList()
)