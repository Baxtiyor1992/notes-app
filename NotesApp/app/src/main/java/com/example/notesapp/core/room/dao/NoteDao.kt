package com.example.notesapp.core.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.notesapp.core.constants.NOTE_TABLE
import com.example.notesapp.core.room.entity.NoteEntity
import com.example.notesapp.core.room.entity.TopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM notes_table WHERE id = :noteId")
    suspend fun getNoteById(noteId: Int): NoteEntity

    @Query("SELECT * FROM $NOTE_TABLE")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM $NOTE_TABLE WHERE topicId = :topicId")
    fun getNotesByTopic(topicId: Int): Flow<List<NoteEntity>>

}