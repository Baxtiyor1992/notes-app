package uz.polat.noteappatto.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.polat.noteappatto.ui.theme.mainFont


@Preview
@Composable
fun ConfirmCancelButton(
    negativeText: String = "cancel",
    positiveText: String = "save",
    onClickCancel: () -> Unit = {},
    onCLickSave: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 24.dp)
    ) {

        Box(modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .clip(RoundedCornerShape(25.dp))
            .clickable { onClickCancel.invoke() }
        ) {
            Text(
                text = "cancel",
                fontSize = 18.sp,
                color = Color.Black,
                fontFamily = mainFont,
                modifier = Modifier
                    .align(Alignment.Center),
                fontWeight = FontWeight.SemiBold
            )

        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(25.dp))
                .background(Color(0xFFcefe48))
                .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(25.dp))
                .clickable { onCLickSave.invoke() }
        ) {
            Text(
                text = positiveText,
                fontSize = 18.sp,
                color = Color.Black,
                fontFamily = mainFont,
                modifier = Modifier
                    .align(Alignment.Center),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


