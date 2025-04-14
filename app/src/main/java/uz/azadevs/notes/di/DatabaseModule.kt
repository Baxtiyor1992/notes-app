package uz.azadevs.notes.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import uz.azadevs.notes.common.mapper.toEntity
import uz.azadevs.notes.data.local.NotesDatabase
import uz.azadevs.notes.domain.model.Topic

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            NotesDatabase::class.java,
            "notes_database"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    get<NotesDatabase>().topicDao.insertAll(
                        listOf(
                            Topic(1, "Personal", "🧠").toEntity(),
                            Topic(2, "Education", "🎓").toEntity(),
                            Topic(3, "Art", "🎨").toEntity(),
                            Topic(4, "Books", "📚").toEntity(),
                            Topic(5, "Games", "🎮").toEntity(),
                        )
                    )
                }
            }
        }).build()
    }

    single {
        get<NotesDatabase>().noteDao
    }

    single {
        get<NotesDatabase>().topicDao
    }

}
