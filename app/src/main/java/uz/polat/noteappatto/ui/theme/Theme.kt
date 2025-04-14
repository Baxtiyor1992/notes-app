package uz.polat.noteappatto.ui.theme

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import timber.log.Timber

private val DarkColorScheme = darkColorScheme(
    primary = darkPrimary,
    background = darkBackground,
    onBackground = darkOnBackground,
    onSurfaceVariant = darkOnSurfaceVariant,
    surface = darkSurface
)

private val LightColorScheme = lightColorScheme(
    primary = lightPrimary,
    background = lightBackground,
    onBackground = lightOnBackground,
    onSurfaceVariant = lightOnSurfaceVariant,
    surface = lightSurface
)

var isDarkMode by mutableStateOf(false)

fun changeAppToDarkMode(isDark: Boolean) {
    Timber.tag("TAG").d("changeAppToDarkMode: isDark:$isDark")
    isDarkMode = isDark
}

@Composable
fun NoteAppAttoTheme(
    darkTheme: Boolean = isDarkMode,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}