package uz.polat.noteappatto.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.polat.noteappatto.utils.navigation.AppNavigationDispatcher
import uz.polat.noteappatto.utils.navigation.AppNavigator
import uz.polat.noteappatto.utils.navigation.NavigationHandler


@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    fun bindAppNavigation(dispatcher: AppNavigationDispatcher): AppNavigator

    @Binds
    fun bindAppNavigationHandler(dispatcher: AppNavigationDispatcher): NavigationHandler

}