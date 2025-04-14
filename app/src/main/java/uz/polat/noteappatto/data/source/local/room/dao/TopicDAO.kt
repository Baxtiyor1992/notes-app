package uz.polat.noteappatto.data.source.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.polat.noteappatto.data.source.local.room.entity.TopicEntity

@Dao
interface TopicDAO {
    @Query("SELECT * FROM TopicEntity")
    fun getTopics(): Flow<List<TopicEntity>>

    @Query("SELECT * FROM topicentity WHERE id = :id")
    fun getTopicById(id: Long): TopicEntity

    @Query("SELECT * FROM TopicEntity WHERE id IN (:topicIds)")
    fun getTopicsByIds(topicIds: List<Long>): Flow<List<TopicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopic(educationEntity: TopicEntity):Long

    @Update
    fun updateTopic(educationEntity: TopicEntity)

    @Delete
    fun deleteTopic(educationEntity: TopicEntity)
}