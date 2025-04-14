package uz.azadevs.notes.features.topic.add

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import org.koin.androidx.compose.koinViewModel
import uz.azadevs.notes.domain.model.Topic
import uz.azadevs.notes.features.topic.add.viewmodel.AddTopicViewModel
import uz.azadevs.notes.features.utils.NoteUtils.topicIcons

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
@Composable
fun AddTopicDialog(
    onSaveClick: () -> Unit,
    onDismiss: () -> Unit,
    viewModel: AddTopicViewModel = koinViewModel()
) {
    var topicName by remember { mutableStateOf("") }

    var selectedIcon by remember { mutableStateOf(topicIcons.first()) }

    val context = LocalContext.current

    val state = viewModel.state.collectAsState()

    if (state.value.error != null) {
        Toast.makeText(context, state.value.error, Toast.LENGTH_SHORT).show()
    }

    if (state.value.isLoading) {
        CircularProgressIndicator()
    }

    AlertDialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        ),
        modifier = Modifier.fillMaxWidth(0.9f),
        onDismissRequest = onDismiss,
        title = { Text("Add Topic") },
        text = {
            Column {
                OutlinedTextField(
                    value = topicName,
                    onValueChange = { topicName = it },
                    label = { Text("Topic Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Choose Icon", style = MaterialTheme.typography.labelMedium)
                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(topicIcons) { icon ->
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(
                                    if (icon == selectedIcon) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.outlineVariant
                                )
                                .clickable { selectedIcon = icon },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = icon,
                                fontSize = 24.sp
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (topicName.isNotBlank()) {
                        viewModel.addTopic(Topic(name = topicName, icon = selectedIcon))
                        onSaveClick()
                    } else {
                        Toast.makeText(context, "Please enter a topic name", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}