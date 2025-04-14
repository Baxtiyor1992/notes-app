package uz.polat.noteappatto.data.source.local.room.entity

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TopicEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val color: Long
)