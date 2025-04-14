package com.example.notesapp.ui.screen.main.home.topic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.notesapp.core.room.entity.NoteEntity
import com.example.notesapp.core.room.entity.TopicEntity
import com.example.notesapp.ui.screen.main.SharedViewModel
import com.example.notesapp.ui.utill.DETAIL_SCREEN
import com.example.notesapp.ui.utill.TOPIC_NOTES

@Composable
fun TopicPage(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController,
    sharedViewModel: SharedViewModel,
    topicPageViewModel: TopicPageViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        topicPageViewModel.getAllTopics()
        topicPageViewModel.getAllNotes()
    }

    val topics by topicPageViewModel.allTopics.collectAsState()
    val notes by topicPageViewModel.allNotes.collectAsState()

    Column() {
        TopicPageBody(
            mainNavController = mainNavController,
            list = topics,
            sharedViewModel = sharedViewModel,
            notes = notes,
            topicPageViewModel = topicPageViewModel
        )
    }
}

@Composable
fun TopicPageBody(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController,
    list: List<TopicEntity>,
    topicPageViewModel: TopicPageViewModel,
    notes: List<NoteEntity>,
    sharedViewModel: SharedViewModel
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = list) { topic ->
            TopicItem(
                topic = topic,
                onTopicClicked = {
                    sharedViewModel.addSortedNotes(sortList(topic.topicTitle, notes))
                    mainNavController.navigate(TOPIC_NOTES)
                },
                onEdit = {
                    mainNavController.navigate(DETAIL_SCREEN)
                },
                onDelete = {
                    topicPageViewModel.deleteTopic(topic)
                }
            )
        }
    }
}

fun sortList(topicId: String, list: List<NoteEntity>): List<NoteEntity> {
    return list.filter { it.topicId == topicId }
}

