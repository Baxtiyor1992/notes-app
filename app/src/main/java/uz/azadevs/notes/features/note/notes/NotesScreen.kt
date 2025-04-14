package uz.azadevs.notes.features.note.notes

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import uz.azadevs.notes.domain.model.Note
import uz.azadevs.notes.features.note.add.AddNoteDialog
import uz.azadevs.notes.features.note.edit.EditNoteDialog
import uz.azadevs.notes.features.note.notes.viewmodel.NotesViewModel
import uz.azadevs.notes.features.utils.components.CustomToolbar

/**
 * Created by : Azamat Kalmurzaev
 * 13/04/25
 */
@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    noteId: Int,
    viewModel: NotesViewModel = koinViewModel(),
    navController: NavController
) {

    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getNotes(noteId)
    }

    if (state.value.error != null) {
        Toast.makeText(LocalContext.current, state.value.error, Toast.LENGTH_SHORT).show()
    }
    if (state.value.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    val addNoteDialog = remember { mutableStateOf(false) }

    val selectedNote = remember { mutableStateOf<Note?>(null) }

    val editNoteDialogVisible = remember { mutableStateOf(false) }

    if (editNoteDialogVisible.value && selectedNote.value != null) {
        EditNoteDialog(
            note = selectedNote.value!!,
            onDismiss = {
                editNoteDialogVisible.value = false
                selectedNote.value = null
            },
            onUpdateClick = {
                editNoteDialogVisible.value = false
                selectedNote.value = null
            }
        )
    }

    if (addNoteDialog.value) {
        AddNoteDialog(
            onSaveClick = {
                addNoteDialog.value = false
            },
            onDismiss = {
                addNoteDialog.value = false
            })
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding() + 20.dp)
    ) {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding()
                ),
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    addNoteDialog.value = true
                }) {
                    Icon(Icons.Filled.Add, contentDescription = "Add Note")
                }
            },
            topBar = {
                CustomToolbar(
                    modifier = modifier
                        .fillMaxWidth(),
                    title = "Personal",
                    isSearchVisible = true,
                    onBackClick = {
                        navController.popBackStack()
                    }, onSearchClick = {})
            }
        ) { padding ->

            if (state.value.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                state.value.error?.let {
                    Toast.makeText(LocalContext.current, it, Toast.LENGTH_SHORT).show()
                }
            }

            state.value.data?.let {
                NotesSection(
                    notes = it,
                    modifier = Modifier.padding(padding),
                    onNoteClick = { note ->
                        selectedNote.value = note
                        editNoteDialogVisible.value = true
                    }
                )
            }

        }
    }
}

@Composable
fun NotesSection(modifier: Modifier = Modifier, notes: List<Note>, onNoteClick: (Note) -> Unit) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(notes) { note ->
            NoteItem(
                note = note,
                onNoteClick = {
                    onNoteClick(note)
                }
            )
        }
    }
}

@Composable
fun NoteItem(modifier: Modifier = Modifier, note: Note, onNoteClick: (Note) -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(16.dp)
            )
            .clickable {
                onNoteClick(note)
            },
        elevation = CardDefaults.elevatedCardElevation(6.dp)
    ) {
        Column(
            modifier = modifier.padding(
                top = 12.dp,
                bottom = 8.dp,
                start = 12.dp,
                end = 12.dp
            )
        ) {
            Text(
                text = note.title,
                modifier = Modifier.padding(bottom = 8.dp),
                maxLines = 2,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = note.description,
                modifier = Modifier.padding(bottom = 8.dp),
                maxLines = 8,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                overflow = TextOverflow.Ellipsis
            )
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Text(
                    text = note.date,
                    modifier = Modifier.padding(bottom = 8.dp),
                    maxLines = 1,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize
                )
            }
        }
    }
}