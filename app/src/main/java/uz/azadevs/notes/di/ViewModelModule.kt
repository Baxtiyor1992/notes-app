package uz.azadevs.notes.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import uz.azadevs.notes.features.home.viewmodel.HomeViewModel
import uz.azadevs.notes.features.note.add.viewmodel.AddNoteViewModel
import uz.azadevs.notes.features.note.edit.EditNoteViewModel
import uz.azadevs.notes.features.note.notes.viewmodel.NotesViewModel
import uz.azadevs.notes.features.topic.add.viewmodel.AddTopicViewModel

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */

val viewModel = module {

    viewModel {
        AddTopicViewModel(
            addTopicUseCase = get()
        )
    }

    viewModel {
        HomeViewModel(
            get()
        )
    }

    viewModel {
        NotesViewModel(
            get()
        )
    }
    viewModel {
        AddNoteViewModel(
            get(),
            get()
        )
    }

    viewModel {
        EditNoteViewModel(
            get(),
            get(),
            get()
        )
    }

}