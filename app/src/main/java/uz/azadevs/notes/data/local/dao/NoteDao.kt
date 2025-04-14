package uz.azadevs.notes.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import uz.azadevs.notes.data.local.entity.NoteEntity

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
@Dao
interface NoteDao {

    @Upsert
    suspend fun upsertNote(noteEntity: NoteEntity)

    @Query("SELECT * from notes ORDER BY created_at ASC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * from notes WHERE note_id = :id")
    fun getNoteById(id: Int): Flow<NoteEntity>

    @Query("DELETE FROM notes WHERE note_id = :id")
    suspend fun deleteNoteById(id: Int)

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM notes WHERE note_title LIKE '%' || :query || '%'")
    fun searchNotes(query: String): Flow<List<NoteEntity>>

}