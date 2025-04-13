package com.example.notesapp.core.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.core.constants.TOPIC_TABLE

@Entity(tableName = TOPIC_TABLE)
data class TopicEntity(
    @PrimaryKey
    var id: Int,
    var topicTitle: String,
    var notes: List<NoteEntity>
)
