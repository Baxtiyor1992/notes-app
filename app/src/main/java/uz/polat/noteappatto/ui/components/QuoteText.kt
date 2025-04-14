package uz.polat.noteappatto.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.polat.noteappatto.R


@Preview
@Composable
fun QuoteText(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String = "",
    color: Color = MaterialTheme.colorScheme.primary,
    isFocusable: Boolean = true,
    onValueChange: (String) -> Unit = {}
) {
    val focusRequest = remember { FocusRequester() }

    Box(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .clip(RoundedCornerShape(8.dp))
                then (
                if (isFocusable) {
                    Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) { focusRequest.requestFocus() }
                } else {
                    Modifier
                }
                )
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0.3f)
                .background(color)
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(4.dp)
                .background(color)
        )

        Icon(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)
                .size(12.dp),
            painter = painterResource(id = R.drawable.ic_quote),
            contentDescription = null,
            tint = color
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 24.dp)
                .padding(16.dp)
        ) {

            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .focusRequester(focusRequest),
                value = text,
                onValueChange = onValueChange,
                enabled = isFocusable,
                textStyle = MaterialTheme.typography.bodyLarge
                    .copy(color = Color.Black),
                decorationBox = { innerTextField ->
                    Box(modifier = Modifier.fillMaxWidth()) {
                        if (text.isEmpty()) {
                            Text(
                                text = stringResource(R.string.your_quote_here),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,


                                )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}

