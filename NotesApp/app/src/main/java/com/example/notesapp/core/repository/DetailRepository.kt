package com.example.notesapp.core.repository

import com.example.notesapp.core.room.db.AppDatabase
import com.example.notesapp.core.room.entity.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailRepository @Inject constructor(private val appDatabase: AppDatabase) {

    suspend fun getNote(id: Int): NoteEntity{
        return withContext(Dispatchers.IO) {
            appDatabase.noteDao().getNoteById(id)
        }
    }
}