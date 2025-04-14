package uz.azadevs.notes.domain.usecase.note.notes

import kotlinx.coroutines.flow.Flow
import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.Note

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
interface GetNotesUseCase {

    operator fun invoke(): Flow<Result<List<Note>>>

}