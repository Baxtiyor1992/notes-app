package uz.azadevs.notes.domain.usecase.note.get_note

import kotlinx.coroutines.flow.Flow
import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.Note

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
interface GetNoteByIdUseCase {

    suspend operator fun invoke(id: Int): Flow<Result<Note>>

}