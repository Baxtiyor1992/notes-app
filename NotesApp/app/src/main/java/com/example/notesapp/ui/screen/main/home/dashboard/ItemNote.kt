package com.example.notesapp.ui.screen.main.home.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.core.room.entity.NoteEntity
import com.example.notesapp.core.room.entity.NoteStatus

@Composable
fun ItemNote(
    modifier: Modifier = Modifier, item: NoteEntity = NoteEntity(
        title = "Doing Homework",
        topic = "Homework",
        content = "hello world hello",
        createdTime = "11/12/2024",
        editedTime = "14/14/1212",
        isPinned = true,
        status = NoteStatus.New
    ), onItemClicked: () -> Unit
) {
    Surface(modifier = modifier
        .fillMaxWidth()
        .clickable { onItemClicked() }
        .padding(vertical = 4.dp, horizontal = 8.dp),
        shadowElevation = 6.dp,
        shape = RoundedCornerShape(16.dp),
        color = Color.White) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                if (item.isPinned) {
                    Text(
                        text = "📌", fontSize = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Topic: ${item.topic}",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = item.status.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = item.content.take(80) + if (item.content.length > 80) "..." else "",
                fontSize = 14.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Created: ${item.createdTime}", fontSize = 12.sp, color = Color.Gray
                )
                Text(
                    text = "Edited: ${item.editedTime}", fontSize = 12.sp, color = Color.Gray
                )
            }
        }
    }
}