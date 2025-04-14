package com.example.notesapp.ui.screen.main.home.dashboard

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
import com.example.notesapp.ui.screen.main.SharedViewModel
import com.example.notesapp.ui.utill.DETAIL_SCREEN

@Composable
fun DashboardPage(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedViewModel,
    mainNavController: NavHostController,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = Unit) {
        dashboardViewModel.getAllNotes()
    }

    val noteList by dashboardViewModel.allNotes.collectAsState()

    Column(
        modifier
            .fillMaxSize()
    ) {
        DashboardBody(
            mainNavController = mainNavController,
            items = noteList,
            sharedViewModel = sharedViewModel,
            dashboardViewModel = dashboardViewModel
        )
    }
}

@Composable
fun DashboardBody(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController,
    items: List<NoteEntity>,
    dashboardViewModel: DashboardViewModel,
    sharedViewModel: SharedViewModel
) {


    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = items) { note ->
            ItemNote(
                item = note,
                onItemClicked = {
                    sharedViewModel.setId(note.id)
                    mainNavController.navigate(DETAIL_SCREEN)
                },
                onEdit = {

                },
                onDelete = {
                    dashboardViewModel.deleteNote(note)
                }
            )
        }
    }

}