package uz.azadevs.notes.features.note.add.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.Note
import uz.azadevs.notes.domain.model.Topic
import uz.azadevs.notes.domain.usecase.note.add_update.AddUpdateNoteUseCase
import uz.azadevs.notes.domain.usecase.topic.topics.GetTopicsUseCase

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
class AddNoteViewModel(
    private val addNoteUseCase: AddUpdateNoteUseCase,
    private val topicsUseCase: GetTopicsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AddNoteUiState())
    val state = _state.asStateFlow()

    init {
        getTopics()
    }

    private fun getTopics() {
        viewModelScope.launch {
            topicsUseCase().collect {
                when (it) {
                    is Result.Failure -> {
                        _state.value = AddNoteUiState(error = it.message, isLoading = false)
                    }

                    is Result.Success -> {
                        _state.value = AddNoteUiState(topics = it.data, isLoading = false)
                    }
                }
            }
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            when (val result = addNoteUseCase(note)) {
                is Result.Failure -> {
                    _state.value = AddNoteUiState(error = result.message, isLoading = false)
                }

                is Result.Success -> {
                    _state.value = AddNoteUiState(error = null, isLoading = false)
                }
            }
        }
    }
}

data class AddNoteUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val topics: List<Topic> = emptyList()
)
