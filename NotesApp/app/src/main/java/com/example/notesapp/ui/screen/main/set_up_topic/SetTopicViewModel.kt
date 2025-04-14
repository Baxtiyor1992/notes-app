package com.example.notesapp.ui.screen.main.set_up_topic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.core.repository.SetTopicRepository
import com.example.notesapp.core.room.entity.NoteEntity
import com.example.notesapp.core.room.entity.TopicEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetTopicViewModel @Inject constructor(private val setTopicRepository: SetTopicRepository) :
    ViewModel() {

    private val _allTopics = MutableStateFlow<List<TopicEntity>>(emptyList())
    val allTopic: StateFlow<List<TopicEntity>> = _allTopics

    fun getAllTopics() {
        viewModelScope.launch {
            setTopicRepository.getAllTopics()
                .collect { notes ->
                    _allTopics.value = notes
                }
        }
    }

    fun addTopic(topicEntity: TopicEntity) {
        viewModelScope.launch {
            setTopicRepository.addTopic(topicEntity)
        }
    }

    fun addNote(noteEntity: NoteEntity) {
        viewModelScope.launch {
            setTopicRepository.addNote(noteEntity)
        }
    }
}