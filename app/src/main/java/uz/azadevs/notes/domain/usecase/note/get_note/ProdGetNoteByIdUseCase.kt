package uz.azadevs.notes.domain.usecase.note.get_note

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.Note
import uz.azadevs.notes.domain.repository.NotesRepository

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
class ProdGetNoteByIdUseCase(
    private val noteRepository: NotesRepository
) : GetNoteByIdUseCase {

    override suspend fun invoke(id: Int): Flow<Result<Note>> {
        return noteRepository.getNoteById(id)
            .map { note ->
                Result.Success(note)
            }
            .catch<Result<Note>> { e ->
                emit(
                    Result.Failure(
                        e.localizedMessage ?: "Unknown error"
                    )
                )
            }
    }
}