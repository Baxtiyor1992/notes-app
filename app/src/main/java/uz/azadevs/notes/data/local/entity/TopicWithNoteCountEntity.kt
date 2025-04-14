package uz.azadevs.notes.data.local.entity

import androidx.room.ColumnInfo

data class TopicWithNoteCountEntity(
    @ColumnInfo(name = "topic_id")
    val id: Long,

    @ColumnInfo(name = "topic_name")
    val title: String,

    @ColumnInfo(name = "topic_icon")
    val icon: String,

    val noteCount: Int
)