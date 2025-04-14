package uz.azadevs.notes.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
data class TopicWithNotesEntity(
    @Embedded val topic: TopicEntity,
    @Relation(
        parentColumn = "topic_id",
        entityColumn = "topic_id"
    )
    val notes: List<NoteEntity>
)
