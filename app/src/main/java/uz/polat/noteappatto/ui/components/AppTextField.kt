package uz.polat.noteappatto.ui.components

import android.R.attr.fontWeight
import android.R.attr.textSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.polat.noteappatto.R
import uz.polat.noteappatto.ui.theme.mainFont


@Preview
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    textSize: Int = 32,
    placeHolder: String = stringResource(R.string.title),
    color: Color = MaterialTheme.colorScheme.onBackground,
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
        textStyle = MaterialTheme.typography.headlineSmall
            .copy(color = color, fontSize = textSize.sp),
        ) { draw ->
        if (value.isEmpty()) {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = textSize.sp),
                color = Color.Gray,
            )
        }
        draw()
    }
}
