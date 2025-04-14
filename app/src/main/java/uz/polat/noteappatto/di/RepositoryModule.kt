package uz.polat.noteappatto.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.polat.noteappatto.data.repository.NoteRepository
import uz.polat.noteappatto.data.repository.NoteRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NoteRepositoryModule {

    @Binds
    @Singleton
    fun provideNoteRepository(impl: NoteRepositoryImpl): NoteRepository
}