package uz.polat.noteappatto.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import uz.polat.noteappatto.R
import uz.polat.noteappatto.ui.theme.mainFont
import uz.polat.noteappatto.ui.theme.plainGreyColor
import uz.polat.noteappatto.utils.TOPIC_COLORS

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TopicAddBottomSheet(
    onDismissRequest: () -> Unit,
    onClickSave: (String, Color) -> Unit,
) {

    var selectedColor by remember { mutableStateOf(TOPIC_COLORS[0]) }

    ModalBottomSheet(
        containerColor = selectedColor,
        onDismissRequest = onDismissRequest,
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
        ) {

            var textValue by remember { mutableStateOf("") }
            Text(
                text = stringResource(R.string.new_topic),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Start),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = plainGreyColor, shape = RoundedCornerShape(24.dp))
                    .padding(vertical = 8.dp)
            ) {

                TextField(
                    value = textValue,
                    onValueChange = {
                        textValue = it
                    },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.Gray,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                    label = {
                        Text(stringResource(R.string.name),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                            )
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = plainGreyColor, shape = RoundedCornerShape(24.dp))
                    .padding(vertical = 16.dp)
            ) {

                Text(
                    stringResource(R.string.color),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                    )

                CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
                    LazyRow(
                        contentPadding = PaddingValues(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(count = TOPIC_COLORS.size) {
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .background(color = TOPIC_COLORS[it], shape = CircleShape)
                                    .border(
                                        width = if (TOPIC_COLORS[it] == selectedColor) 4.dp else 0.dp,
                                        color = if (TOPIC_COLORS[it] == selectedColor) Color.Black else Color.Transparent,
                                        shape = CircleShape
                                    )
                                    .clickable {
                                        selectedColor = TOPIC_COLORS[it]
                                    }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            SaveCancelButton(
                onCLickSave = {
                    onClickSave(textValue, selectedColor)
                },
                onClickCancel = onDismissRequest
            )


        }


    }
}


@Preview
@Composable
private fun AppPreview() {

}