package uz.azadevs.notes.domain.usecase.topic.topics_with_count

import kotlinx.coroutines.flow.Flow
import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.TopicWithNoteCount

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
interface GetTopicsWithNoteCountUseCase {

    suspend operator fun invoke(): Flow<Result<List<TopicWithNoteCount>>>

}