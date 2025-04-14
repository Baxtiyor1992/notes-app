package uz.azadevs.notes.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import uz.azadevs.notes.domain.usecase.note.add_update.AddUpdateNoteUseCase
import uz.azadevs.notes.domain.usecase.note.add_update.ProdAddUpdateNoteUseCase
import uz.azadevs.notes.domain.usecase.note.delete.DeleteNoteUseCase
import uz.azadevs.notes.domain.usecase.note.delete.ProdDeleteNoteUseCase
import uz.azadevs.notes.domain.usecase.note.get_note.GetNoteByIdUseCase
import uz.azadevs.notes.domain.usecase.note.get_note.ProdGetNoteByIdUseCase
import uz.azadevs.notes.domain.usecase.note.notes.GetNotesUseCase
import uz.azadevs.notes.domain.usecase.note.notes.ProdGetNotesUseCase
import uz.azadevs.notes.domain.usecase.topic.add.AddTopicUseCase
import uz.azadevs.notes.domain.usecase.topic.add.ProdAddTopicUseCase
import uz.azadevs.notes.domain.usecase.topic.topics.GetTopicsUseCase
import uz.azadevs.notes.domain.usecase.topic.topics.ProdGetTopicsUseCase
import uz.azadevs.notes.domain.usecase.topic.topics_with_count.GetTopicsWithNoteCountUseCase
import uz.azadevs.notes.domain.usecase.topic.topics_with_count.ProdGetTopicsWithNoteCountUseCase
import uz.azadevs.notes.domain.usecase.topic.topics_with_notes.GetTopicWithNotesUseCase
import uz.azadevs.notes.domain.usecase.topic.topics_with_notes.ProdGetTopicWithNotesUseCase

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */

val useCaseModule = module {

    factoryOf(::ProdGetNotesUseCase) bind GetNotesUseCase::class

    factoryOf(::ProdDeleteNoteUseCase) bind DeleteNoteUseCase::class

    factoryOf(::ProdAddUpdateNoteUseCase) bind AddUpdateNoteUseCase::class

    factoryOf(::ProdAddTopicUseCase) bind AddTopicUseCase::class

    factoryOf(::ProdGetTopicsUseCase) bind GetTopicsUseCase::class

    factoryOf(::ProdGetTopicsWithNoteCountUseCase) bind GetTopicsWithNoteCountUseCase::class

    factoryOf(::ProdGetTopicWithNotesUseCase) bind GetTopicWithNotesUseCase::class

    factoryOf(::ProdGetNoteByIdUseCase) bind GetNoteByIdUseCase::class

}