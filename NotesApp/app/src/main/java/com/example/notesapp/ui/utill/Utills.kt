package com.example.notesapp.ui.utill

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.ui.theme.PrimaryColor

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

@Preview
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String = "hello",
    onValueChange: (String) -> Unit = {},
    label: String = "world",
    hint: String = "",
    endIcon: ImageVector = Icons.Default.Clear,
    onClearClicked: () -> Unit = {},
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    isSingleLine: Boolean = true,
    maxLines: Int = 1
) {

    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged(onFocusChanged = { focusState -> isFocused = focusState.isFocused }),
        label = {
            Text(text = label, color = if (isFocused) PrimaryColor else Color.LightGray)
        },
        placeholder = {
            Text(text = hint)
        },
        trailingIcon = {
            IconButton(onClick = {
                onClearClicked()
            }) {
                Icon(
                    imageVector = endIcon,
                    contentDescription = "icon",
                    tint = if (isFocused) PrimaryColor else Color.LightGray
                )
            }
        },
        isError = isError,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.Done),
        singleLine = isSingleLine,
        maxLines = maxLines,
        minLines = 1,
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = PrimaryColor,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        )
    )
}


@Preview(showSystemUi = true)
@Composable
fun CustomAlertDialog(
    title: String = "Delete Note",
    message: String = "do u wanna delete it",
    confirmButtonText: String = "cancel ",
    dismissButtonText: String = "delete",
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
    onDialogDismissRequest: () -> Unit = {}
) {
    AlertDialog(
        onDismissRequest = onDialogDismissRequest,
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
                color = Color.Black
            )
        },
        text = {
            Text(
                text = message,
                fontSize = 16.sp,
                color = Color.DarkGray
            )
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm()
            }) {
                Text(
                    text = confirmButtonText,
                    color = Color(0xFFAE7EF8),
                    fontSize = 16.sp
                )
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismiss()
            }) {
                Text(
                    text = dismissButtonText,
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        }
    )
}

@Composable
fun TextFieldDialog(
    modifier: Modifier = Modifier,
    title: String,
    textValue: String,
    onValueChange: (String) -> Unit,
    confirmButtonText: String,
    dismissButtonText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onDialogDismissRequest: () -> Unit
) {
    AlertDialog(
        containerColor = Color.White,
        onDismissRequest = onDialogDismissRequest,
        title = {
            Text(text = title, fontSize = 20.sp)
        },
        text = {
            TextField(
                value = textValue,
                onValueChange = onValueChange,
                placeholder = { Text("Topic name") },
                singleLine = true,
                textStyle = TextStyle(fontSize = 22.sp),
                modifier = modifier,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = dismissButtonText)
            }
        }
    )
}
