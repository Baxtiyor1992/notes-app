package uz.polat.noteappatto.data.source.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.polat.noteappatto.data.source.local.room.entity.NoteEntity
import uz.polat.noteappatto.data.source.local.room.entity.TopicEntity

@Dao
interface NoteDAO {

    @Query("SELECT * FROM NoteEntity ORDER BY createdTime DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM noteentity WHERE id = :id")
    fun getNoteById(id: Long): NoteEntity


    @Insert
    fun insertNote(note: NoteEntity): Long

    @Update
    fun updateNote(noteEntity: NoteEntity)

    @Delete
    fun deleteNote(noteEntity: NoteEntity)
}