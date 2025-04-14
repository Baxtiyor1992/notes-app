package uz.polat.noteappatto.data.source.local.room.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity()
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val topicIds: List<Long> = emptyList(),
    val createdTime: Long,
    val noteDatas: List<NoteData>,
    val colors: List<Long> = emptyList()
)

@Parcelize
sealed interface NoteData : Parcelable {
    @Parcelize
    data class Title(var title: String) : NoteData

    @Parcelize
    data class Text(var text: String) : NoteData

    @Parcelize
    data class Img(var uri: String) : NoteData

    @Parcelize
    data class QuotedText(var quote: String) : NoteData
}