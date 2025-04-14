package com.example.notesapp.ui.screen.main.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.notesapp.R
import com.example.notesapp.core.room.entity.NoteEntity
import com.example.notesapp.core.room.entity.NoteStatus
import com.example.notesapp.ui.screen.main.SharedViewModel
import com.example.notesapp.ui.screen.main.home.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    mainNavHostController: NavHostController,
    sharedViewModel: SharedViewModel,
    detailViewModel: DetailViewModel = hiltViewModel()
) {

    val note by detailViewModel.note.collectAsState()

    LaunchedEffect(Unit) {
        val noteId = sharedViewModel.noteId
        detailViewModel.getNote(noteId)
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                startIcon = painterResource(R.drawable.ic_back),
                endIconClicked = {},
                startIconClicked = {
                    mainNavHostController.navigateUp()
                },
                endIcon = null,
                currentIndex = 4,
            )
        }) { paddingValues ->
        paddingValues
        DetailBody(
            modifier = modifier.padding(paddingValues), note = note
                ?: NoteEntity(
                    title = "Random Note Title",
                    content = "This is a randomly generated note content. It can be long enough to simulate a real note with random data.",
                    isPinned = false,
                    topicId = "",
                    createdTime = "",
                    editedTime = "",
                    status = NoteStatus.New
                )
        )
    }

}

@Composable
fun DetailBody(modifier: Modifier = Modifier, note: NoteEntity) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            if (note.isPinned) {
                Text(
                    text = "📌 Pinned",
                    color = Color(0xFFAE7EF8),
                    fontSize = 16.sp
                )
            }

            Text(
                text = statusSticker(note.status),
                fontSize = 18.sp
            )
        }

        TopicBadge(topic = note.topicId)

        Text(
            text = note.title,
            fontSize = 28.sp,
            color = Color.Black
        )

        Text(
            text = note.content,
            fontSize = 18.sp,
            color = Color.DarkGray,
            modifier = Modifier
                .background(Color(0xFFF1ECFA), RoundedCornerShape(14.dp))
                .padding(18.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "🕒 Created: ${note.createdTime}",
                fontSize = 13.sp,
                color = Color.Gray
            )

            if (note.editedTime.isNotBlank()) {
                Text(
                    text = "✏️ Edited: ${note.editedTime}",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "📌 Status:",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Text(
                    text = note.status.name,
                    color = statusColor(note.status),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun TopicBadge(topic: String) {
    Text(
        text = "🏷️ $topic",
        color = Color.White,
        fontSize = 13.sp,
        modifier = Modifier
            .background(Color(0xFFAE7EF8), RoundedCornerShape(50))
            .padding(horizontal = 14.dp, vertical = 6.dp)
    )
}

@Composable
fun statusColor(status: NoteStatus): Color {
    return when (status) {
        NoteStatus.New -> Color.Blue
        NoteStatus.InProgress -> Color(0xFFAE7EF8)
        NoteStatus.Completed -> Color(0xFF4CAF50)
        NoteStatus.Deleted -> Color.Red
        NoteStatus.Important -> Color(0xFFFFA000)
    }
}

@Composable
fun statusSticker(status: NoteStatus): String {
    return when (status) {
        NoteStatus.New -> "🆕"
        NoteStatus.InProgress -> "🚧"
        NoteStatus.Completed -> "✅"
        NoteStatus.Deleted -> "🗑️"
        NoteStatus.Important -> "⭐"
    }
}



