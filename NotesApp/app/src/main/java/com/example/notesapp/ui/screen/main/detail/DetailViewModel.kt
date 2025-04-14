package com.example.notesapp.ui.screen.main.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.core.repository.DetailRepository
import com.example.notesapp.core.room.entity.NoteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailRepository: DetailRepository) :
    ViewModel() {

    private val _note = MutableStateFlow<NoteEntity?>(
        value = null
    )
    val note: StateFlow<NoteEntity?> = _note

    fun getNote(id: Int) {
        viewModelScope.launch {
            val note = detailRepository.getNote(id)
            _note.value = note
        }
    }


}