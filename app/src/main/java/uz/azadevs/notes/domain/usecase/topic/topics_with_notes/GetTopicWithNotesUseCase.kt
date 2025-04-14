package uz.azadevs.notes.domain.usecase.topic.topics_with_notes

import kotlinx.coroutines.flow.Flow
import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.TopicWithNotes

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
interface GetTopicWithNotesUseCase {

    suspend operator fun invoke(id: Int): Flow<Result<TopicWithNotes>>

}