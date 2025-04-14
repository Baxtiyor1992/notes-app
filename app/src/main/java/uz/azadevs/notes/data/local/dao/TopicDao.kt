package uz.azadevs.notes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import uz.azadevs.notes.data.local.entity.TopicEntity
import uz.azadevs.notes.data.local.entity.TopicWithNoteCountEntity
import uz.azadevs.notes.data.local.entity.TopicWithNotesEntity

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
@Dao
interface TopicDao {

    @Upsert
    suspend fun upsertTopic(topicEntity: TopicEntity)

    @Query("SELECT * FROM topics")
    fun getAllTopics(): Flow<List<TopicEntity>>

    @Query("SELECT * FROM topics WHERE topic_id = :id")
    fun getTopicById(id: Int): Flow<TopicEntity>

    @Transaction
    @Query("SELECT * FROM topics WHERE topic_id = :id")
    fun getTopicWithNotes(id: Int): Flow<TopicWithNotesEntity>

    @Query(
        """
    SELECT t.topic_id, t.topic_name, t.topic_icon, COUNT(n.note_id) as noteCount
    FROM topics t
    LEFT JOIN notes n ON t.topic_id = n.topic_id
    GROUP BY t.topic_id
"""
    )
    fun getTopicsWithNoteCount(): Flow<List<TopicWithNoteCountEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(items: List<TopicEntity>)

    @Query("SELECT COUNT(*) FROM topics")
    suspend fun getCount(): Int


}