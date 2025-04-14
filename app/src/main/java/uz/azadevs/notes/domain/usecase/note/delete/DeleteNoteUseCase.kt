package uz.azadevs.notes.domain.usecase.note.delete

import uz.azadevs.notes.common.model.Result

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
interface DeleteNoteUseCase {

    suspend operator fun invoke(id: Int) : Result<Unit>

}