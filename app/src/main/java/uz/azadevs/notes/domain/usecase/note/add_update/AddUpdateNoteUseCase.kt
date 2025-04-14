package uz.azadevs.notes.domain.usecase.note.add_update

import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.Note

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
interface AddUpdateNoteUseCase {

    suspend operator fun invoke(note: Note): Result<Unit>

}