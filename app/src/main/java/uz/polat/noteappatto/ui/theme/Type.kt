package uz.polat.noteappatto.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import uz.polat.noteappatto.R

val mainFont = FontFamily(
    Font(R.font.full_bold, FontWeight.Bold),
    Font(R.font.full_regular, FontWeight.Normal),
    Font(R.font.full_semibold, FontWeight.SemiBold),
    Font(R.font.full_medium, FontWeight.Medium),
)


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)