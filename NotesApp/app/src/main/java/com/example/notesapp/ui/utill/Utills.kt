package com.example.notesapp.ui.utill

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun CustomSpacer(
    modifier: Modifier = Modifier, height: Int = 0, width: Int = 0
) {
    Spacer(
        modifier = modifier
            .width(width.dp)
            .height(height.dp)
    )
}

@Composable
fun TypingText(
    modifier: Modifier = Modifier,
    text: String,
    speed: Long = 100L,
    timeIsUp: () -> Unit = { },
) {
    var displayedText by remember { mutableStateOf("") }
    var finished by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        displayedText = ""
        text.forEachIndexed { index, _ ->
            delay(speed)
            displayedText = text.substring(0, index + 1)
        }
        delay(500)
        timeIsUp.invoke()
        finished = true

    }

    Text(
        text = displayedText, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White
    )
}