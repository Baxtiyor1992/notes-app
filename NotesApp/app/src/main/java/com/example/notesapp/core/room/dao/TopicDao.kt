package com.example.notesapp.core.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.notesapp.core.room.entity.TopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopicDao {

    @Insert(onConflict = REPLACE)
    suspend fun addTopic(topicEntity: TopicEntity)

    @Query("SELECT * FROM topics_table")
    fun getAllTopic(): Flow<List<TopicEntity>>

    @Delete
    suspend fun deleteTopic(topicEntity: TopicEntity)

}