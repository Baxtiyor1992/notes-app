package uz.azadevs.notes.features.utils

import kotlinx.serialization.Serializable

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
@Serializable
object HomeScreen

@Serializable
data class NotesScreen(
    val noteId: Int
)