package uz.polat.noteappatto.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import uz.polat.noteappatto.BuildConfig

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}