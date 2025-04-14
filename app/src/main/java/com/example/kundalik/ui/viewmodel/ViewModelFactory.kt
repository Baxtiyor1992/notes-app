package com.example.kundalik.ui.viewmodel

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val mApplication: Application): ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE : ViewModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application):ViewModelFactory{
            if(INSTANCE==null){
                synchronized(ViewModelFactory::class.java){
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)){
        return NotesViewModel(mApplication) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
}
}
fun obtainViewModel(activity: AppCompatActivity): NotesViewModel {
    val factory = ViewModelFactory.getInstance(activity.application)
    return ViewModelProvider(activity, factory).get(NotesViewModel::class.java)

}