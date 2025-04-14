package uz.azadevs.notes.features.note.add

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import org.koin.androidx.compose.koinViewModel
import uz.azadevs.notes.common.utils.DateUtils
import uz.azadevs.notes.domain.model.Note
import uz.azadevs.notes.domain.model.Topic
import uz.azadevs.notes.features.note.add.viewmodel.AddNoteViewModel

/**
 * Created by : Azamat Kalmurzaev
 * 14/04/25
 */
@Composable
fun AddNoteDialog(
    onSaveClick: () -> Unit,
    onDismiss: () -> Unit,
    viewModel: AddNoteViewModel = koinViewModel()
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedTopic by remember { mutableStateOf<Topic?>(null) }
    val state = viewModel.state.collectAsState()

    if (state.value.error != null) {
        Toast.makeText(LocalContext.current, state.value.error, Toast.LENGTH_SHORT).show()
    }

    LaunchedEffect(state.value.topics) {
        if (state.value.topics.isNotEmpty()) {
            selectedTopic = state.value.topics.first()
        }
    }

    if (state.value.isLoading) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    selectedTopic?.let {
                        viewModel.addNote(
                            note = Note(
                                title = title,
                                description = description,
                                topicId = it.id,
                                date = DateUtils.convertLongToTime(System.currentTimeMillis()),
                                id = 0,
                                topic = selectedTopic?.name ?: "Unknown"
                            )
                        )
                        onSaveClick()
                    }
                },
                enabled = title.isNotBlank() && selectedTopic != null
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Add Note") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box {
                    OutlinedTextField(
                        value = selectedTopic?.name ?: "Select Topic",
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                expanded = true
                            }
                            .focusable(false),
                        readOnly = true,
                        label = { Text("Topic") },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                modifier = Modifier.clickable {
                                    expanded = true
                                }
                            )
                        }
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        state.value.topics.forEach { topic ->
                            DropdownMenuItem(
                                text = { Text(topic.name) },
                                onClick = {
                                    selectedTopic = topic
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        },
        modifier = Modifier.fillMaxWidth(0.9f),
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    )
}