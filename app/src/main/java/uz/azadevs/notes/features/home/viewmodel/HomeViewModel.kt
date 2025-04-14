package uz.azadevs.notes.features.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.TopicWithNoteCount
import uz.azadevs.notes.domain.usecase.topic.topics_with_count.GetTopicsWithNoteCountUseCase

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
class HomeViewModel(
    private val getTopicsWithNoteCountUseCase: GetTopicsWithNoteCountUseCase
) : ViewModel() {

    val state = flow {
        emit(HomeUiState(isLoading = true))
        getTopicsWithNoteCountUseCase()
            .collect { result ->
                when (result) {
                    is Result.Failure -> {
                        emit(HomeUiState(error = result.message, isLoading = false))
                    }

                    is Result.Success -> {
                        emit(HomeUiState(data = result.data, isLoading = false))
                    }
                }
            }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState()
    )

}

data class HomeUiState(
    val data: List<TopicWithNoteCount>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)