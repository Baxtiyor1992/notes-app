package uz.azadevs.notes.features.topic.add.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.Topic
import uz.azadevs.notes.domain.usecase.topic.add.AddTopicUseCase

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */

class AddTopicViewModel(
    private val addTopicUseCase: AddTopicUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(AddTopicState())
    val state get() = _state.asStateFlow()

    fun addTopic(topic: Topic) {
        viewModelScope.launch {
            when (val result = addTopicUseCase(topic)) {
                is Result.Failure -> {
                    _state.value = _state.value.copy(
                        error = result.message,
                        isLoading = false
                    )
                }

                is Result.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }
}

data class AddTopicState(
    val isLoading: Boolean = false,
    val error: String? = null
)