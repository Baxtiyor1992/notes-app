package uz.azadevs.notes.domain.usecase.topic.topics

import kotlinx.coroutines.flow.Flow
import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.Topic

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
interface GetTopicsUseCase {

    suspend operator fun invoke(): Flow<Result<List<Topic>>>

}