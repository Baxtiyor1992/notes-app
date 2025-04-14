package com.example.notesapp.core.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesapp.core.room.dao.NoteDao
import com.example.notesapp.core.room.dao.TopicDao
import com.example.notesapp.core.room.entity.NoteEntity
import com.example.notesapp.core.room.entity.TopicEntity

@Database(entities = [NoteEntity::class, TopicEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun topicDao(): TopicDao
}
