package uz.polat.noteappatto.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.polat.noteappatto.data.source.local.room.dao.NoteDAO
import uz.polat.noteappatto.data.source.local.room.dao.TopicDAO
import uz.polat.noteappatto.data.source.local.room.database.NoteDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTopicDao(database: NoteDatabase): TopicDAO {
        return database.getTopicDAO()
    }

    @Provides
    @Singleton
    fun provideNoteDao(database: NoteDatabase): NoteDAO {
        return database.getNoteDAO()
    }
}