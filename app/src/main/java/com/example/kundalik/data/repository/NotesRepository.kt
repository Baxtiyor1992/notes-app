package com.example.kundalik.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.kundalik.data.local.NotesDao
import com.example.kundalik.data.local.NotesDatabase
import com.example.kundalik.domain.Notes
import kotlinx.coroutines.flow.Flow

class NotesRepository(application: Application) {

    private val mNotesDao: NotesDao

    init {
        val notesDatabase = NotesDatabase.getDatabase(application)
        mNotesDao = notesDatabase.notesDao()
    }

    fun getAllNotes(): Flow<List<Notes>> = mNotesDao.getAllNotes()

    suspend fun insert(notes: Notes) {
        mNotesDao.insert(notes)
    }

    suspend fun delete(notes: Notes) {
        mNotesDao.delete(notes)
    }

    suspend fun update(notes: Notes) {
        mNotesDao.update(notes)
    }

    fun searchNotes(searchQuery: String): Flow<List<Notes>> = mNotesDao.searchNotes(searchQuery)
}