package com.example.kundalik.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kundalik.domain.Notes

@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): NotesDatabase {
            if (INSTANCE == null) {
                synchronized(NotesDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java,
                        "kundalik"
                    ).build()
                }
            }
            return INSTANCE as NotesDatabase
        }
    }
}
