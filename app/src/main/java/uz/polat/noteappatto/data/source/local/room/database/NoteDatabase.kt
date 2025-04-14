package uz.polat.noteappatto.data.source.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.polat.noteappatto.data.source.local.room.converter.Converter
import uz.polat.noteappatto.data.source.local.room.dao.NoteDAO
import uz.polat.noteappatto.data.source.local.room.dao.TopicDAO
import uz.polat.noteappatto.data.source.local.room.entity.NoteEntity
import uz.polat.noteappatto.data.source.local.room.entity.TopicEntity


@Database(entities = [TopicEntity::class ,NoteEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getTopicDAO(): TopicDAO
    abstract fun getNoteDAO(): NoteDAO
}