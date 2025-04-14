package uz.azadevs.notes.domain.model

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
data class TopicWithNoteCount(
    val id: Long = 0,
    val title: String,
    val icon: String,
    val noteCount: Int = 0
)
