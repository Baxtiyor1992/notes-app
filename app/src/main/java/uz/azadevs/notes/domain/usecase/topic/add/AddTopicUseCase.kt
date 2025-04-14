package uz.azadevs.notes.domain.usecase.topic.add

import uz.azadevs.notes.common.model.Result
import uz.azadevs.notes.domain.model.Topic

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
interface AddTopicUseCase {

    suspend operator fun invoke(topic: Topic): Result<Unit>

}