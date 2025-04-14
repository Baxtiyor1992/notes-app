package com.example.notesapp.core.repository

import com.example.notesapp.core.room.db.AppDatabase
import com.example.notesapp.core.room.entity.NoteEntity
import com.example.notesapp.core.room.entity.TopicEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TopicsPageRepository @Inject constructor(private val appDatabase: AppDatabase) {

    fun getAllTopics(): Flow<List<TopicEntity>> {
        return appDatabase.topicDao().getAllTopic()
    }

    fun getAllNotes(): Flow<List<NoteEntity>>{
        return appDatabase.noteDao().getAllNotes()
    }

    suspend fun deleteTopic(topicEntity: TopicEntity){
        withContext(Dispatchers.IO)
        {
            appDatabase.topicDao().deleteTopic(topicEntity)
        }
    }

}