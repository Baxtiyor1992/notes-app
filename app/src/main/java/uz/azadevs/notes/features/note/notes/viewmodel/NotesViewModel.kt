package uz.azadevs.notes.features.note.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.Note
import uz.azadevs.notes.domain.usecase.topic.topics_with_notes.ProdGetTopicWithNotesUseCase

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
class NotesViewModel(
    val notesUseCase: ProdGetTopicWithNotesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NotesUiState())
    val state = _state.asStateFlow()

    fun getNotes(id: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true
            )
            notesUseCase(id).collect {
                when (it) {
                    is Result.Failure -> {
                        _state.value = _state.value.copy(
                            error = it.message,
                            isLoading = false
                        )
                    }

                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            data = it.data.notes,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}

data class NotesUiState(
    val data: List<Note>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)