package uz.azadevs.notes.domain.usecase.topic.topics_with_notes

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.TopicWithNotes
import uz.azadevs.notes.domain.repository.TopicsRepository

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
class ProdGetTopicWithNotesUseCase(
    private val topicsRepository: TopicsRepository
) : GetTopicWithNotesUseCase {

    override suspend fun invoke(id: Int): Flow<Result<TopicWithNotes>> {
        return topicsRepository.getTopicsWithNotes(id)
            .map {
                Result.Success(it)
            }.catch<Result<TopicWithNotes>> {
                emit(
                    Result.Failure(it.localizedMessage ?: "Unknown error")
                )
            }
    }
}