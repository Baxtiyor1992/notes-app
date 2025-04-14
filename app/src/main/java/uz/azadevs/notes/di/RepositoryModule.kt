package uz.azadevs.notes.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import uz.azadevs.notes.data.repository.ProdNotesRepository
import uz.azadevs.notes.data.repository.ProdTopicRepository
import uz.azadevs.notes.domain.repository.NotesRepository
import uz.azadevs.notes.domain.repository.TopicsRepository

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */

val repositoryModule = module {

    singleOf(::ProdNotesRepository) bind NotesRepository::class

    singleOf(::ProdTopicRepository) bind TopicsRepository::class

}