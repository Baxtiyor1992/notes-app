package uz.azadevs.notes.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.azadevs.notes.domain.model.Note

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
interface NotesRepository {

    suspend fun upsertNote(note: Note)

    fun getAllNotes(): Flow<List<Note>>

    fun getNoteById(id: Int): Flow<Note>

    suspend fun deleteAllNotes()

    suspend fun deleteNoteById(id: Int)

}