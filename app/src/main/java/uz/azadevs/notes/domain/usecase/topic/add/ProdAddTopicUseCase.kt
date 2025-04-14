package uz.azadevs.notes.domain.usecase.topic.add

import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.Topic
import uz.azadevs.notes.domain.repository.TopicsRepository

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
class ProdAddTopicUseCase(
    private val topicsRepository: TopicsRepository
) : AddTopicUseCase {

    override suspend fun invoke(topic: Topic): Result<Unit> {
        val validationResult = validateTopic(topic)
        if (validationResult is Result.Failure) {
            return validationResult
        }
        topicsRepository.upsertTopic(topic)
        return Result.Success(Unit)
    }

    private fun validateTopic(topic: Topic): Result<Unit> {
        if (topic.name.isBlank()) {
            return Result.Failure("Title can't be empty")
        }
        return Result.Success(Unit)
    }
}