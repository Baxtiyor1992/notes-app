package uz.polat.noteappatto.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.polat.noteappatto.ui.theme.mainFont


@Preview
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    placeHolder: String = "Title",
    textSize: TextUnit = 16.sp,
    color: Color = Color.Black,
    lineHeightStyle: TextUnit = 24.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    onValueChange: (String) -> Unit = {},
) {
    val focus = remember { mutableStateOf(false) }

    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
            .then(if (focus.value) Modifier.imePadding() else Modifier),
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            color = color,
            fontSize = textSize,
            fontWeight = fontWeight,
            fontFamily = mainFont,
        )
    ) { draw ->
        if (value.isEmpty()) {
            Text(
                text = placeHolder,
                fontSize = textSize,
                color = Color.Gray,
                fontFamily = mainFont,
                fontWeight = FontWeight.Medium
            )
        }
        draw()
    }
}
