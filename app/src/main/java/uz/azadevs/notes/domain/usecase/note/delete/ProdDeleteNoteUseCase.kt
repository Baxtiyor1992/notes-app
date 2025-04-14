package uz.azadevs.notes.domain.usecase.note.delete

import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.repository.NotesRepository

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
class ProdDeleteNoteUseCase(
    private val notesRepository: NotesRepository
) : DeleteNoteUseCase {

    override suspend fun invoke(id: Int): Result<Unit> {
        return try {
            notesRepository.deleteNoteById(id)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Failure(e.localizedMessage ?: "Failed to delete note")
        }
    }
}