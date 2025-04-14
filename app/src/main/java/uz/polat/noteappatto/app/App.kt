package uz.polat.noteappatto.app

import android.app.Application
import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import uz.polat.noteappatto.BuildConfig
import uz.polat.noteappatto.data.source.local.sharedPref.LocalStorage
import javax.inject.Inject

@HiltAndroidApp
class App(): Application(
) {



    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }


    }
}