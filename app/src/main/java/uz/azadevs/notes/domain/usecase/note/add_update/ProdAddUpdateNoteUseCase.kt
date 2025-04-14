package uz.azadevs.notes.domain.usecase.note.add_update

import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.Note
import uz.azadevs.notes.domain.repository.NotesRepository

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
class ProdAddUpdateNoteUseCase(
    private val notesRepository: NotesRepository
) : AddUpdateNoteUseCase {

    override suspend fun invoke(note: Note): Result<Unit> {
        val validationResult = validateNote(note)
        if (validationResult is Result.Failure) {
            return validationResult
        }
        notesRepository.upsertNote(note)
        return Result.Success(Unit)
    }

    private fun validateNote(note: Note): Result<Unit> {
        if (note.title.isBlank()) {
            return Result.Failure("Title can't be empty")
        }
        if (note.description.isBlank()) {
            return Result.Failure("Description can't be empty")
        }
        if (note.topic.isBlank()) {
            return Result.Failure("Topic can't be empty")
        }
        return Result.Success(Unit)
    }
}