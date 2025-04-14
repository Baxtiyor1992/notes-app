package uz.polat.noteappatto.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.polat.noteappatto.ui.screens.main.MainScreenContracts
import uz.polat.noteappatto.ui.screens.main.MainScreenDirections
import uz.polat.noteappatto.ui.screens.note.NoteScreenContracts
import uz.polat.noteappatto.ui.screens.note.NoteScreenDirections
import uz.polat.noteappatto.ui.screens.onboarding.OnBoardingContracts
import uz.polat.noteappatto.ui.screens.onboarding.OnBoardingDirections
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module

interface DirectionsModule {

    @[Binds Singleton]
    fun bindOnBoardingScreenDirection(impl: OnBoardingDirections): OnBoardingContracts.Directions

    @[Binds Singleton]
    fun bindMainScreenDirections(impl: MainScreenDirections): MainScreenContracts.Directions

    @[Binds Singleton]
    fun bindNoteScreenDirections(impl: NoteScreenDirections): NoteScreenContracts.Directions
}