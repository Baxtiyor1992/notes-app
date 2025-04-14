package com.example.notesapp.ui.screen.main.home.topic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.core.room.entity.TopicEntity
import com.example.notesapp.ui.theme.PrimaryColor
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@Composable
fun TopicItem(
    modifier: Modifier = Modifier,
    topic: TopicEntity,
    onTopicClicked: () -> Unit = {},
    onDelete: (TopicEntity) -> Unit = {},
    onEdit: (TopicEntity) -> Unit = {}
) {
    val deleteAction = SwipeAction(
        icon = {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Topic",
                tint = Color.White
            )
        },
        background = Color.Red,
        onSwipe = {
            onDelete(topic)
        }
    )

    val editAction = SwipeAction(
        icon = {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit Topic",
                tint = Color.White
            )
        },
        background = Color.Blue,
        onSwipe = {
            onEdit(topic)
        }
    )

    SwipeableActionsBox(
        startActions = listOf(editAction),
        endActions = listOf(deleteAction)
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onTopicClicked() }
                .padding(horizontal = 12.dp, vertical = 6.dp),
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 6.dp,
            color = Color.White
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(14.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(6.dp)
                        .height(50.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(color = PrimaryColor)
                )

                Spacer(modifier = Modifier.width(12.dp))

                val emoji = when (topic.topicTitle.lowercase()) {
                    "work" -> "💼"
                    "personal" -> "🏡"
                    "study" -> "📚"
                    "fitness" -> "🏋️"
                    "travel" -> "✈️"
                    else -> "📝"
                }

                Surface(
                    modifier = Modifier
                        .size(50.dp)
                        .graphicsLayer {
                            rotationZ = -8f
                            shadowElevation = 10f
                        },
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 6.dp
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = emoji,
                            fontSize = 28.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = topic.topicTitle,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

