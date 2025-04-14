package uz.azadevs.notes.features.utils.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
@Composable
fun CustomToolbar(
    modifier: Modifier,
    title: String,
    isSearchVisible: Boolean,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background)
            .padding(all = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back",
            modifier = Modifier
                .size(24.dp)
                .clip(shape = CircleShape)
                .clickable {
                    onBackClick()
                },
            tint = MaterialTheme.colorScheme.primary
        )

        Text(
            text = title,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.SemiBold,
        )
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            modifier = Modifier
                .alpha(
                    if (isSearchVisible) 1f else 0f
                )
                .clip(shape = CircleShape)
                .clickable {
                    if (isSearchVisible) onSearchClick()
                }
                .size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )

    }
}