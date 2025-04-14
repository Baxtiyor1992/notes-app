package uz.azadevs.notes.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
@Entity("topics")
data class TopicEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("topic_id") val id: Int,
    @ColumnInfo("topic_name") val name: String,
    @ColumnInfo("topic_icon") val icon: String
)
