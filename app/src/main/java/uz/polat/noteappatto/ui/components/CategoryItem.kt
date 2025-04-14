package uz.polat.noteappatto.ui.components

import android.R.attr.name
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.polat.noteappatto.data.source.local.room.entity.TopicEntity
import uz.polat.noteappatto.ui.theme.mainFont
import uz.polat.noteappatto.utils.TOPIC_ADD
import uz.polat.noteappatto.utils.TOPIC_ALL
import uz.polat.noteappatto.utils.TOPIC_ALL_ID


@Composable
fun CategoryItem(
    topic: TopicEntity,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val isAddTopic = topic.name == TOPIC_ADD
    val isAllTopic = topic.id == TOPIC_ALL_ID
    val selectedColor = if(isAllTopic) MaterialTheme.colorScheme.primary
        else Color(topic.color.toULong())
    Box(
        modifier = Modifier
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
                if (isAddTopic) CircleShape else RoundedCornerShape(8.dp))
            .clip(if (isAddTopic) CircleShape else RoundedCornerShape(8.dp))
            .background(if (isSelected) selectedColor else Color.Transparent)
            .clickable { onClick.invoke() }
            .padding(vertical = if(isAddTopic) 8.dp else 12.dp, horizontal = 16.dp)
    ) {

        if(isAddTopic){
            Icon(imageVector = Icons.Rounded.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(0.dp)
                )
        }else{
            Text(
                text = topic.name,
                style = MaterialTheme.typography.bodyLarge,
                color = if(isAllTopic && isSelected) Color.Black else MaterialTheme.colorScheme.onBackground
            )
        }


    }
}
