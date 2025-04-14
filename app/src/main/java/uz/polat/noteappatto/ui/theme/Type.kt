package uz.polat.noteappatto.ui.theme

import android.R.attr.fontFamily
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import uz.polat.noteappatto.R
import uz.polat.noteappatto.ui.theme.mainFont

val mainFont = FontFamily(
    Font(R.font.full_bold, FontWeight.Bold),
    Font(R.font.full_regular, FontWeight.Normal),
    Font(R.font.full_semibold, FontWeight.SemiBold),
    Font(R.font.full_medium, FontWeight.Medium),
)


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = mainFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = mainFont,
    ),
    displayLarge = TextStyle(
        fontFamily = mainFont,
        fontWeight = FontWeight.Medium,
        fontSize = 100.sp,
        lineHeight = 100.sp
    ),

    headlineLarge = TextStyle(
        fontSize = 40.sp,
        fontFamily = mainFont,
        fontWeight = FontWeight.SemiBold,
    ),

    headlineSmall = TextStyle(
        fontSize = 32.sp,
        fontFamily = mainFont,
        fontWeight = FontWeight.SemiBold,
    ),

    titleLarge = TextStyle(
        fontFamily = mainFont,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
    )

)