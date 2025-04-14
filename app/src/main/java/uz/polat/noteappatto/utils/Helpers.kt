package uz.polat.noteappatto.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import uz.polat.noteappatto.ui.theme.color1
import uz.polat.noteappatto.ui.theme.color10
import uz.polat.noteappatto.ui.theme.color11
import uz.polat.noteappatto.ui.theme.color12
import uz.polat.noteappatto.ui.theme.color13
import uz.polat.noteappatto.ui.theme.color14
import uz.polat.noteappatto.ui.theme.color15
import uz.polat.noteappatto.ui.theme.color16
import uz.polat.noteappatto.ui.theme.color17
import uz.polat.noteappatto.ui.theme.color18
import uz.polat.noteappatto.ui.theme.color19
import uz.polat.noteappatto.ui.theme.color2
import uz.polat.noteappatto.ui.theme.color3
import uz.polat.noteappatto.ui.theme.color4
import uz.polat.noteappatto.ui.theme.color5
import uz.polat.noteappatto.ui.theme.color6
import uz.polat.noteappatto.ui.theme.color7
import uz.polat.noteappatto.ui.theme.color8
import uz.polat.noteappatto.ui.theme.color9
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val TOPIC_ALL = "All"
const val TOPIC_ALL_ID: Long = -1
const val TOPIC_ADD = "+"
const val TOPIC_ADD_ID: Long = -2

val TOPIC_COLORS = listOf<Color>(
    color1, color2, color3, color4, color5, color6, color7, color8, color9, color10,
    color11, color12, color13, color14, color15, color16, color17, color18, color19,
    )

fun getDateTimeAsString(time: Long): String {
    val dateFormat = SimpleDateFormat("dd MMM, HH:mm", Locale.getDefault())
    val date = Date(time)
    return dateFormat.format(date)
}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}