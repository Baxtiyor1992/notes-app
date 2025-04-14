package uz.polat.noteappatto.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import uz.polat.noteappatto.ui.theme.mainFont

@Composable
fun CustomDialog(
    title: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
                .padding(24.dp)
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontFamily = mainFont,
                fontWeight = FontWeight.SemiBold,
            )

            Spacer(modifier = Modifier.height(24.dp))

            ConfirmCancelButton(
                positiveText = "Yes",
                onClickCancel = onDismiss,
                onCLickSave = onConfirm,
//                onClickConfirm = onConfirm
            )
        }
    }
}