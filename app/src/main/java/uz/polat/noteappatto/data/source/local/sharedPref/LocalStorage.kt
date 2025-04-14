package uz.polat.noteappatto.data.source.local.sharedPref

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class LocalStorage @Inject constructor(@ApplicationContext context: Context) : SharedPreferenceHelper(context) {

    var isFirstLaunch by booleans(true)
}

