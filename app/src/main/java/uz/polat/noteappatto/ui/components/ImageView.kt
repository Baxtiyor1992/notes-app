package uz.polat.noteappatto.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImageView(

    modifier: Modifier = Modifier
        .fillMaxSize(),
    uri: String
) {
    Box(
        modifier = modifier
//            .padding(24.dp)
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(30.dp))
    ) {
        AsyncImage(
            modifier = modifier,
            model = uri,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}