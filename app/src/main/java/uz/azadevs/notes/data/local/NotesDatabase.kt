package uz.azadevs.notes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.azadevs.notes.data.local.dao.NoteDao
import uz.azadevs.notes.data.local.dao.TopicDao
import uz.azadevs.notes.data.local.entity.NoteEntity
import uz.azadevs.notes.data.local.entity.TopicEntity

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
@Database(entities = [NoteEntity::class, TopicEntity::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    abstract val topicDao: TopicDao

}