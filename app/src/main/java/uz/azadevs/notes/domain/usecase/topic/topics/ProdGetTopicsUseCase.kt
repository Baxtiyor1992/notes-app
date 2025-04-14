package uz.azadevs.notes.domain.usecase.topic.topics

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.Topic
import uz.azadevs.notes.domain.repository.TopicsRepository

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
class ProdGetTopicsUseCase(
    private val topicsRepository: TopicsRepository
) : GetTopicsUseCase {

    override suspend fun invoke(): Flow<Result<List<Topic>>> {
        return topicsRepository.getAllTopics()
            .map { topics -> Result.Success(topics) }
            .catch<Result<List<Topic>>> {
                emit(Result.Failure(it.message ?: "Something went wrong"))
            }
    }
}