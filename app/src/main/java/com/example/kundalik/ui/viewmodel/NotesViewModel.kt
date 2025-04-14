package com.example.kundalik.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.kundalik.data.repository.NotesRepository
import com.example.kundalik.domain.Notes
import kotlinx.coroutines.launch

class NotesViewModel(application : Application):ViewModel() {
    private val mNotesRepository : NotesRepository = NotesRepository(application)
    fun insert(notes: Notes){
        viewModelScope.launch {
            mNotesRepository.insert(notes)
        }
    }
    val allNotes : LiveData<List<Notes>> = mNotesRepository.getAllNotes().asLiveData()

    fun delete(notes: Notes){
        viewModelScope.launch {
            mNotesRepository.delete(notes)
        }
    }

    fun update(notes: Notes){
        viewModelScope.launch {
            mNotesRepository.update(notes)
        }
    }
    fun searchNotes(searchQuery: String): LiveData<List<Notes>> {
        return mNotesRepository.searchNotes(searchQuery).asLiveData()
    }

}