package uz.azadevs.notes.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.azadevs.notes.common.mapper.toEntity
import uz.azadevs.notes.common.mapper.toNote
import uz.azadevs.notes.common.mapper.toNotes
import uz.azadevs.notes.data.local.dao.NoteDao
import uz.azadevs.notes.domain.model.Note
import uz.azadevs.notes.domain.repository.NotesRepository

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
class ProdNotesRepository(
    private val noteDao: NoteDao
) : NotesRepository {

    override suspend fun upsertNote(note: Note) = noteDao.upsertNote(note.toEntity())

    override fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes().map { notes ->
        notes.toNotes()
    }

    override fun getNoteById(id: Int): Flow<Note> = noteDao.getNoteById(id).map { entity ->
        entity.toNote()
    }

    override suspend fun deleteNoteById(id: Int) = noteDao.deleteNoteById(id)

    override suspend fun deleteAllNotes() = noteDao.deleteAllNotes()
}