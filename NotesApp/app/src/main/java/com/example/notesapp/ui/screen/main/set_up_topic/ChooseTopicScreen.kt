package com.example.notesapp.ui.screen.main.set_up_topic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.notesapp.R
import com.example.notesapp.core.room.entity.NoteEntity
import com.example.notesapp.core.room.entity.NoteStatus
import com.example.notesapp.core.room.entity.TopicEntity
import com.example.notesapp.ui.screen.main.SharedViewModel
import com.example.notesapp.ui.screen.main.home.CustomTopAppBar
import com.example.notesapp.ui.screen.main.home.topic.TopicItem
import com.example.notesapp.ui.utill.CustomAlertDialog
import com.example.notesapp.ui.utill.TextFieldDialog

@Composable
fun SetUpTopic(
    modifier: Modifier = Modifier,
    mainNavHostController: NavHostController,
    sharedViewModel: SharedViewModel,
    setTopicViewModel: SetTopicViewModel = hiltViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedTopic by remember { mutableStateOf("") }
    var topic by remember { mutableStateOf("") }
    var showTextFieldDialog by remember { mutableStateOf(false) }

    val topics by setTopicViewModel.allTopic.collectAsState()

    LaunchedEffect(key1 = Unit) {
        setTopicViewModel.getAllTopics()
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                endIconClicked = {
                    showTextFieldDialog = true
                },
                startIconClicked = { mainNavHostController.navigateUp() },
                startIcon = painterResource(R.drawable.ic_back),
                endIcon = painterResource(R.drawable.ic_add),
                currentIndex = 3
            )

            if (showTextFieldDialog) TextFieldDialog(
                title = "Add new topic",
                textValue = topic,
                onValueChange = {
                    topic = it
                },
                confirmButtonText = "Add",
                dismissButtonText = "Cancel",
                onConfirm = {
                    if (topic.isNotEmpty()) {
                        setTopicViewModel.addTopic(TopicEntity(topicTitle = topic))
                    }
                    showTextFieldDialog = false
                },
                onDismiss = {
                    showTextFieldDialog = false
                },
                onDialogDismissRequest = {
                    showTextFieldDialog = false
                })
        }) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items = topics) { topic ->
                TopicItem(topic = topic, onTopicClicked = {
                    showDialog = true
                    selectedTopic = topic.topicTitle
                })
            }
        }

        if (showDialog) {
            CustomAlertDialog(
                title = "Add Note",
                message = "Do you want to add a note to \"$selectedTopic\"?",
                confirmButtonText = "Yes",
                dismissButtonText = "Cancel",
                onConfirm = {
                    showDialog = false
                    addNote(
                        title = sharedViewModel.title,
                        note = sharedViewModel.note,
                        viewModel = setTopicViewModel,
                        topicId = selectedTopic
                    )
                },
                onDismiss = { showDialog = false },
                onDialogDismissRequest = { showDialog = false })
        }
    }

}

fun addNote(title: String, note: String, viewModel: SetTopicViewModel, topicId: String) {
    val noteEntity = NoteEntity(
        topicId = topicId,
        title = title,
        content = note,
        createdTime = System.currentTimeMillis().toString(),
        editedTime = "",
        isPinned = false,
        status = NoteStatus.New
    )
    viewModel.addNote(noteEntity)
}
