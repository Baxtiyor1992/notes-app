package com.example.notesapp.ui.screen.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.notesapp.core.room.entity.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {

    var noteId by mutableIntStateOf(-1)
        private set

    var sortedNotes = MutableStateFlow<List<NoteEntity>>(emptyList())
        private set

    var title by mutableStateOf("")
        private set

    var note by mutableStateOf("")
        private set

    fun setId(id: Int) {
        this.noteId = id
    }

    fun addNote(title: String, note: String) {
        this.title = title
        this.note = note
    }

    fun addSortedNotes(notes: List<NoteEntity>) {
        sortedNotes.value = notes
    }
}