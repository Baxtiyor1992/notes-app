package uz.polat.noteappatto.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.polat.noteappatto.data.source.local.room.entity.NoteData
import uz.polat.noteappatto.data.source.local.room.entity.NoteEntity
import uz.polat.noteappatto.utils.getDateTimeAsString


@Composable
fun NoteItem(
    noteEntity: NoteEntity,
    onClickNoteItem: () -> Unit = {}
) {
    Column {
        Text(
            text = getDateTimeAsString(noteEntity.createdTime),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 24.dp, bottom = 4.dp)
        )

        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
        ) {
            Box(
                modifier = Modifier
                    .alpha(0.4f)
                    .padding(start = 24.dp)
                    .fillMaxHeight()
                    .width(8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.onSurfaceVariant)
            )

            Box(
                modifier = Modifier
                    .weight(0.9f)
                    .padding(start = 16.dp, end = 12.dp)
                    .heightIn(0.dp, 300.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .clickable { onClickNoteItem.invoke() }
            ) {

                Row(modifier = Modifier.matchParentSize()) {
                    if (noteEntity.colors.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                    } else {
                        noteEntity.colors.forEach { color ->
                            Box(
                                modifier = Modifier
                                    .background(Color(color.toULong()))
                                    .fillMaxHeight()
                                    .weight(1f)
                            )
                        }
                    }

                }

                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        text = (noteEntity.noteDatas.first() as NoteData.Title).title,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black
                    )

                    if (noteEntity.noteDatas.size > 1) {
                        when (val it = noteEntity.noteDatas[1]) {
                            is NoteData.Text -> {
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = it.text,
                                    color = Color.Black,
                                    style = MaterialTheme.typography.bodyLarge,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }


                            is NoteData.Img -> {
                                ImageView(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(300.dp),
                                    uri = it.uri
                                )
                            }

                            is NoteData.QuotedText -> {
                                Spacer(modifier = Modifier.height(16.dp))
                                QuoteText(
                                    text = it.quote,
                                    isFocusable = false,
                                    color = Color.White
                                )
                            }

                            else -> {}
                        }
                    }

                }
            }

        }
    }
}


@Preview
@Composable
fun NoteItemPreview() {

}
