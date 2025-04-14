package com.example.notesapp.ui.screen.main.topic_notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.notesapp.R
import com.example.notesapp.core.room.entity.NoteEntity
import com.example.notesapp.ui.screen.main.SharedViewModel
import com.example.notesapp.ui.screen.main.home.CustomTopAppBar
import com.example.notesapp.ui.screen.main.home.dashboard.ItemNote
import com.example.notesapp.ui.utill.DETAIL_SCREEN

@Composable
fun TopicNotesScreen(
    modifier: Modifier = Modifier,
    mainNavHostController: NavHostController,
    sharedViewModel: SharedViewModel,
) {
    val notes by sharedViewModel.sortedNotes.collectAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                startIcon = painterResource(R.drawable.ic_back),
                endIconClicked = {},
                startIconClicked = {
                    mainNavHostController.navigateUp()
                },
                endIcon = null,
                currentIndex = 5,
            )
        }) { paddingValues ->

        TopicNotesBody(
            modifier = modifier.padding(paddingValues),
            mainNavController = mainNavHostController,
            list = notes
        )
    }
}

@Composable
fun TopicNotesBody(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController,
    list: List<NoteEntity>
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = list) { note ->
            ItemNote(item = note, onItemClicked = {
                mainNavController.navigate(DETAIL_SCREEN)
            })
        }
    }
}