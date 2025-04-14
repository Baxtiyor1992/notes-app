package uz.azadevs.notes.domain.usecase.note.notes

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.Note
import uz.azadevs.notes.domain.repository.NotesRepository

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
class ProdGetNotesUseCase(
    private val notesRepository: NotesRepository
) : GetNotesUseCase {

    override fun invoke(): Flow<Result<List<Note>>> {
        return notesRepository.getAllNotes()
            .map { notes ->
                Result.Success(notes)
            }
            .catch<Result<List<Note>>> { e ->
                emit(
                    Result.Failure(
                        e.localizedMessage ?: "Unknown error"
                    )
                )
            }
    }

}