package com.example.notesapp.core.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.core.constants.NOTE_TABLE

@Entity(tableName = NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val topicId: String,
    val title: String,
    val content: String,
    val createdTime: String = System.currentTimeMillis().toString(),
    var editedTime: String = "",
    var isPinned: Boolean = false,
    var status: NoteStatus = NoteStatus.New,
)


