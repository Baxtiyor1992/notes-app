package com.example.notesapp.ui.screen.main.home.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.core.repository.DashboardRepository
import com.example.notesapp.core.room.entity.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
) : ViewModel() {

    private val _allNotes = MutableStateFlow<List<NoteEntity>>(emptyList())
    val allNotes: StateFlow<List<NoteEntity>> = _allNotes

    fun getAllNotes() {
        viewModelScope.launch {
            dashboardRepository.getAllNotes()
                .collect { notes ->
                    _allNotes.value = notes
                }
        }
    }

    fun deleteNote(noteEntity: NoteEntity) {
        viewModelScope.launch {
            dashboardRepository.deleteNote(noteEntity = noteEntity)
        }
    }

}
