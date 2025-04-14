package uz.azadevs.notes.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.azadevs.notes.common.mapper.toEntity
import uz.azadevs.notes.common.mapper.toTopic
import uz.azadevs.notes.common.mapper.toTopicWithNoteCounts
import uz.azadevs.notes.common.mapper.toTopicWithNotes
import uz.azadevs.notes.common.mapper.toTopics
import uz.azadevs.notes.data.local.dao.TopicDao
import uz.azadevs.notes.data.local.entity.TopicWithNotesEntity
import uz.azadevs.notes.domain.model.Topic
import uz.azadevs.notes.domain.model.TopicWithNoteCount
import uz.azadevs.notes.domain.model.TopicWithNotes
import uz.azadevs.notes.domain.repository.TopicsRepository

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
class ProdTopicRepository(
    private val topicDao: TopicDao
) : TopicsRepository {

    override suspend fun upsertTopic(topic: Topic) {
        topicDao.upsertTopic(topic.toEntity())
    }

    override fun getAllTopics(): Flow<List<Topic>> {
        return topicDao.getAllTopics().map { entities -> entities.toTopics() }
    }

    override fun getTopicById(id: Int): Flow<Topic> {
        return topicDao.getTopicById(id).map { it.toTopic() }
    }

    override fun getTopicsWithNotes(id: Int): Flow<TopicWithNotes> {
        return topicDao.getTopicWithNotes(id).map { value: TopicWithNotesEntity ->
            value.toTopicWithNotes()
        }
    }

    override fun getTopicsWithNoteCount(): Flow<List<TopicWithNoteCount>> {
        return topicDao.getTopicsWithNoteCount().map { entities ->
            entities.toTopicWithNoteCounts()
        }
    }
}