package uz.polat.noteappatto.data.source.local.room.converter


import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter
import uz.polat.noteappatto.data.source.local.room.entity.NoteData
import kotlin.collections.joinToString

class Converter {

//    @TypeConverter
//    fun fromColor(color: Color): Long {
//        return color.value.toLong()
//    }
//
//    @TypeConverter
//    fun toColor(value: Long): Color {
//        return Color(value.toULong())
//    }
//
//    @TypeConverter
//    fun fromColorList(colors: List<Color>): String {
//        return colors.joinToString(",") { it.value.toString() }
//    }
//
//    @TypeConverter
//    fun toColorList(data: String): List<Color> {
//        return if (data.isEmpty()) emptyList()
//        else data.split(",").map { Color(it.toULong()) }
//    }

    @TypeConverter
    fun fromLongList(list: List<Long>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toLongList(data: String): List<Long> {
        return if (data.isEmpty()) emptyList()
        else data.split(",").map { it.toLong() }
    }

    @TypeConverter
    fun fromNoteDataList(noteList: List<NoteData>): String {
        return serializeNoteDataList(noteList)
    }

    @TypeConverter
    fun toNoteDataList(data: String): List<NoteData> {
        return deserializeNoteDataList(data)
    }


    private fun serializeNoteData(noteData: NoteData): String {
        return when (noteData) {
            is NoteData.Title -> "0:${noteData.title}"
            is NoteData.Text -> "1:${noteData.text}"
            is NoteData.Img -> "2:${(noteData.uri).trim()}"
            is NoteData.QuotedText -> "3:${noteData.quote}"
        }
    }

    private fun deserializeNoteData(data: String): NoteData {
        val parts = data.split(":", limit = 2)
        val type = parts[0].toIntOrNull() ?: throw IllegalArgumentException("Unknown type")

        return when (type) {
            0 -> NoteData.Title(parts.getOrNull(1)?.trim() ?: "")
            1 -> NoteData.Text(parts.getOrNull(1)?.trim() ?: "")
            2 -> NoteData.Img(parts.getOrNull(1)?.trim() ?: "")
            3 -> NoteData.QuotedText(parts.getOrNull(1)?.trim() ?: "")
            else -> throw IllegalArgumentException("Unknown type: $type")
        }
    }

    private fun serializeNoteDataList(noteList: List<NoteData>): String {
        return noteList.joinToString(" ;") { serializeNoteData(it) }
    }

    private fun deserializeNoteDataList(data: String): List<NoteData> {
        val noteDataList = ArrayList<NoteData>()
        val items = data.split(";")

        for (item in items) {
            noteDataList.add(deserializeNoteData(item))
        }

        return noteDataList
    }


}