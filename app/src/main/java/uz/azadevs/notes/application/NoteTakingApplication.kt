package uz.azadevs.notes.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import uz.azadevs.notes.di.databaseModule
import uz.azadevs.notes.di.repositoryModule
import uz.azadevs.notes.di.useCaseModule
import uz.azadevs.notes.di.viewModel

/**
 * Created by : Azamat Kalmurzaev
 * 12/04/25
 */
class NoteTakingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NoteTakingApplication)
            modules(databaseModule, repositoryModule, useCaseModule, viewModel)
        }
    }
}