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


@Composable
fun CategoryItem(
    topic: TopicEntity,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val isAddTopic = topic.name == "+"
    val selectedColor = if(topic.name == TOPIC_ALL) Color(0xFFcefe48) else Color(topic.color.toULong())
    Box(
        modifier = Modifier
            .border(BorderStroke(1.dp, Color.Black), if (isAddTopic) CircleShape else RoundedCornerShape(8.dp))
            .clip(if (isAddTopic) CircleShape else RoundedCornerShape(8.dp))
            .background(if (isSelected) selectedColor else Color.Transparent)
            .clickable { onClick.invoke() }
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {

        if(isAddTopic){
            Icon(imageVector = Icons.Rounded.Add,contentDescription = null)
        }else{
            Text(
                text = "${topic.name}",
                fontFamily = mainFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }


    }
}
