package uz.azadevs.notes.domain.usecase.topic.topics_with_count

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.TopicWithNoteCount
import uz.azadevs.notes.domain.repository.TopicsRepository

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
class ProdGetTopicsWithNoteCountUseCase(
    private val topicsRepository: TopicsRepository
) : GetTopicsWithNoteCountUseCase {

    override suspend fun invoke(): Flow<Result<List<TopicWithNoteCount>>> {
        return topicsRepository.getTopicsWithNoteCount()
            .map {
                Result.Success(it)
            }
            .catch<Result<List<TopicWithNoteCount>>> { e ->
                emit(
                    Result.Failure(e.localizedMessage ?: "Unknown error")
                )
            }
    }
}