package com.example.notesapp.ui.screen.main.home.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.core.room.entity.NoteEntity
import com.example.notesapp.ui.theme.PrimaryColor
import com.example.notesapp.ui.utill.DETAIL_SCREEN

@Preview(showSystemUi = true)
@Composable
fun DashboardPage(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController = rememberNavController()
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        DashboardBody(
            mainNavController = mainNavController,
            items =
        )
    }
}

@Composable
fun DashboardBody(
    modifier: Modifier = Modifier,
    mainNavController: NavHostController,
    items: List<NoteEntity>
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "All Notes",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = PrimaryColor,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )


        LazyColumn(
            modifier = modifier.weight(1f),
            contentPadding = PaddingValues(vertical = 16.dp, horizontal = 30.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items = items) { note ->
                ItemNote(
                    item = note,
                    onItemClicked = {
                        mainNavController.navigate(DETAIL_SCREEN)
                    }
                )
            }
        }
    }
}