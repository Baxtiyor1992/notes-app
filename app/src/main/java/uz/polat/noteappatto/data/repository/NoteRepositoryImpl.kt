package uz.polat.noteappatto.data.repository

import android.content.ClipData.newUri
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import uz.polat.noteappatto.data.source.local.room.dao.NoteDAO
import uz.polat.noteappatto.data.source.local.room.dao.TopicDAO
import uz.polat.noteappatto.data.source.local.room.entity.NoteEntity
import uz.polat.noteappatto.data.source.local.room.entity.TopicEntity
import uz.polat.noteappatto.data.source.local.sharedPref.LocalStorage
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject
import kotlin.io.copyTo

class NoteRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val topicDao: TopicDAO,
    private val noteDao: NoteDAO,
    private val localStorage: LocalStorage
) : NoteRepository {

    //topics
    override suspend fun getAllTopics(): Flow<List<TopicEntity>> = flow {
        topicDao.getTopics().collect { emit(it) }
    }.flowOn(Dispatchers.IO)

    override suspend fun createTopic(topicEntity: TopicEntity): Flow<TopicEntity> = flow {
        val id = topicDao.insertTopic(topicEntity)
        emit(topicDao.getTopicById(id))
    }.flowOn(Dispatchers.IO)

    override suspend fun updateTopic(topicEntity: TopicEntity): Flow<Unit> = flow {
        topicDao.updateTopic(topicEntity)
        emit(Unit)
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteTopic(topicEntity: TopicEntity): Flow<Unit> = flow {
        topicDao.deleteTopic(topicEntity)
        emit(Unit)
    }.flowOn(Dispatchers.IO)

    override suspend fun getTopicsByIds(topicIds: List<Long>): Flow<List<TopicEntity>> =
        topicDao.getTopicsByIds(topicIds).flowOn(Dispatchers.IO)

    //notes
    override suspend fun getAllNotes(): Flow<List<NoteEntity>> = noteDao.getAllNotes().flowOn(Dispatchers.IO)

    override suspend fun createNote(noteEntity: NoteEntity): Flow<NoteEntity> = flow {
        val id = noteDao.insertNote(noteEntity)
        emit(noteDao.getNoteById(id))
    }.flowOn(Dispatchers.IO)

    override suspend fun updateNote(noteEntity: NoteEntity): Flow<Unit> = flow {
        noteDao.updateNote(noteEntity)
        emit(Unit)
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteNote(noteEntity: NoteEntity): Flow<Unit> = flow {
        noteDao.deleteNote(noteEntity)
        emit(Unit)
    }.flowOn(Dispatchers.IO)

    override suspend fun getNotesByTopic(topicEntity: TopicEntity): Flow<List<NoteEntity>> =
        noteDao.getAllNotes()
            .map { notes -> notes.filter { it.topicIds.contains(topicEntity.id) } }.flowOn(Dispatchers.IO)

    override suspend fun copyImageToAppStorage(uri: Uri): Flow<Uri> = flow {
        val imageName = "image_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, imageName)

        context.contentResolver.openInputStream(uri)?.use { inputStream ->
            FileOutputStream(file).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }

        emit(Uri.fromFile(file))

    }.flowOn(Dispatchers.IO)

    override fun isFirstLaunch(): Boolean {
        return localStorage.isFirstLaunch
    }

}