package uz.azadevs.notes.domain.model

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
data class TopicWithNotes(
    val topic: Topic,
    val notes: List<Note>
)
