package com.example.notesapp.core.repository

import com.example.notesapp.core.room.db.AppDatabase
import com.example.notesapp.core.room.entity.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DashboardRepository @Inject constructor(private val appDatabase: AppDatabase) {

    fun getAllNotes(): Flow<List<NoteEntity>> {
        return appDatabase.noteDao().getAllNotes()
    }

    suspend fun deleteNote(noteEntity: NoteEntity){
        withContext(Dispatchers.IO) {
            appDatabase.noteDao().deleteNote(noteEntity)
        }
    }
}