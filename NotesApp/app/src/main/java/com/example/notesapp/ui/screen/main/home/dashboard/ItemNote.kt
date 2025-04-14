package com.example.notesapp.ui.screen.main.home.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.core.room.entity.NoteEntity
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@Composable
fun ItemNote(
    modifier: Modifier = Modifier,
    item: NoteEntity,
    onItemClicked: () -> Unit = {},
    onDelete: (NoteEntity) -> Unit = {},
    onEdit: (NoteEntity) -> Unit = {}
) {
    val mainColor = Color(0xFFAE7EF8)

    val deleteAction = SwipeAction(icon = {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Note",
            tint = Color.White
        )
    }, background = Color.Red, onSwipe = { onDelete(item) })

    val editAction = SwipeAction(icon = {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit Note",
            tint = Color.White
        )
    }, background = Color.Blue, onSwipe = { onEdit(item) })

    SwipeableActionsBox(
        startActions = listOf(editAction),
        endActions = listOf(deleteAction)
    ) {
        Surface(modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClicked() }
            .padding(vertical = 6.dp, horizontal = 10.dp),
            shadowElevation = 8.dp,
            shape = RoundedCornerShape(16.dp),
            color = Color.White) {
            Row(modifier = Modifier.padding(12.dp)) {
                Box(
                    modifier = Modifier
                        .width(5.dp)
                        .height(90.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(mainColor)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = item.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        if (item.isPinned) {
                            Text(
                                text = "📌", fontSize = 22.sp, color = mainColor
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "🏷️ ${item.topicId}",
                        fontSize = 14.sp,
                        color = mainColor,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = item.content.take(70) + if (item.content.length > 70) "..." else "",
                        fontSize = 14.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Created: ${item.createdTime}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )

                        Text(
                            text = "Edited: ${item.editedTime}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = item.status.name,
                        fontSize = 12.sp,
                        color = mainColor,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

