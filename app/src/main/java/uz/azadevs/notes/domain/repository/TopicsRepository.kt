package uz.azadevs.notes.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.azadevs.notes.domain.model.Topic
import uz.azadevs.notes.domain.model.TopicWithNoteCount
import uz.azadevs.notes.domain.model.TopicWithNotes

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
interface TopicsRepository {

    suspend fun upsertTopic(topic: Topic)

    fun getAllTopics(): Flow<List<Topic>>

    fun getTopicById(id: Int): Flow<Topic>

    fun getTopicsWithNotes(id: Int): Flow<TopicWithNotes>

    fun getTopicsWithNoteCount(): Flow<List<TopicWithNoteCount>>

}