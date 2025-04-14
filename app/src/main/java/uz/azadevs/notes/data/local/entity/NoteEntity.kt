package uz.azadevs.notes.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
@Entity("notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("note_id") val id: Int,
    @ColumnInfo("note_title") val title: String,
    @ColumnInfo("note_description") val description: String,
    @ColumnInfo("note_topic") val topic: String,
    @ColumnInfo("created_at") val createdAt: Long,
    @ColumnInfo("topic_id") val topicId: Int,
)
