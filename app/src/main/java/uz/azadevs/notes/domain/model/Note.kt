package uz.azadevs.notes.domain.model

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
data class Note(
    val id: Int,
    val title: String,
    val description: String,
    val topic: String,
    val date: String,
    val topicId: Int
)
