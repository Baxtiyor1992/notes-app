package com.example.notesapp.core.repository

import com.example.notesapp.core.room.db.AppDatabase
import com.example.notesapp.core.room.entity.NoteEntity
import com.example.notesapp.core.room.entity.TopicEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SetTopicRepository @Inject constructor(private val appDatabase: AppDatabase) {

    fun getAllTopics(): Flow<List<TopicEntity>> {
        return appDatabase.topicDao().getAllTopic()
    }

    suspend fun addTopic(topicEntity: TopicEntity) {
        withContext(Dispatchers.IO) {
            appDatabase.topicDao().addTopic(topicEntity)
        }
    }

    suspend fun addNote(noteEntity: NoteEntity) {
        withContext(Dispatchers.IO) {
            appDatabase.noteDao().addNote(noteEntity)
        }
    }
}