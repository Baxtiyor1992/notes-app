package uz.azadevs.notes.common.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
object DateUtils {

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        return format.format(date)
    }

    fun currentTimeToLong(): Long {
        return System.currentTimeMillis()
    }

    fun convertDateToLong(date: String): Long {
        val df = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        return df.parse(date)?.time ?: 0
    }

}