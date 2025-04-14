package com.example.notesapp.ui.screen.main.home.topic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.core.repository.TopicsPageRepository
import com.example.notesapp.core.room.entity.NoteEntity
import com.example.notesapp.core.room.entity.TopicEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicPageViewModel @Inject constructor(private val topicsPageRepository: TopicsPageRepository) :
    ViewModel(){

    private val _allTopics = MutableStateFlow<List<TopicEntity>>(emptyList())
    val allTopics: StateFlow<List<TopicEntity>> = _allTopics

    private val _allNotes = MutableStateFlow<List<NoteEntity>>(emptyList())
    val allNotes: StateFlow<List<NoteEntity>> = _allNotes

    fun getAllTopics() {
        viewModelScope.launch {
            topicsPageRepository.getAllTopics()
                .collect { notes ->
                    _allTopics.value = notes
                }
        }
    }

    fun getAllNotes() {
        viewModelScope.launch {
            topicsPageRepository.getAllNotes()
                .collect { notes ->
                    _allNotes.value = notes
                }
        }
    }

    fun deleteTopic(topicEntity: TopicEntity){
        viewModelScope.launch {
            topicsPageRepository.deleteTopic(topicEntity)
        }
    }

}