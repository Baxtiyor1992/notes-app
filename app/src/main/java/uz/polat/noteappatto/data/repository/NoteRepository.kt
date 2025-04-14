package uz.polat.noteappatto.data.repository

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import uz.polat.noteappatto.data.source.local.room.entity.NoteEntity
import uz.polat.noteappatto.data.source.local.room.entity.TopicEntity

interface NoteRepository {
    suspend fun getAllTopics(): Flow<List<TopicEntity>>
    suspend fun createTopic(topicEntity: TopicEntity): Flow<TopicEntity>
    suspend fun updateTopic(topicEntity: TopicEntity):Flow<Unit>
    suspend fun deleteTopic(topicEntity: TopicEntity): Flow<Unit>
    suspend fun getTopicsByIds(topicIds: List<Long>): Flow<List<TopicEntity>>


    suspend fun getAllNotes(): Flow<List<NoteEntity>>
    suspend fun createNote(noteEntity: NoteEntity): Flow<NoteEntity>
    suspend fun updateNote(noteEntity: NoteEntity):Flow<Unit>
    suspend fun deleteNote(noteEntity: NoteEntity): Flow<Unit>
    suspend fun getNotesByTopic(topicEntity: TopicEntity): Flow<List<NoteEntity>>


    suspend fun copyImageToAppStorage(uri: Uri): Flow<Uri>


    fun isFirstLaunch(): Boolean
}