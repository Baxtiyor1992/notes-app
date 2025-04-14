package com.example.notesapp.ui.screen.main.home.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.notesapp.ui.screen.main.SharedViewModel
import com.example.notesapp.ui.theme.PrimaryColor
import com.example.notesapp.ui.utill.CHOOSE_TOPIC
import com.example.notesapp.ui.utill.CustomTextField


@Composable
fun AddNotePage(
    modifier: Modifier = Modifier,
    mainNavHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    var title by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = modifier.weight(0.1f))
        CustomTextField(
            text = title,
            label = "Note Title",
            onValueChange = { newValue -> title = newValue },
            onClearClicked = {
                title = ""
            })
        Spacer(modifier = modifier.weight(0.1f))

        CustomTextField(
            text = note,
            modifier = modifier.weight(1f),
            onValueChange = { newValue -> note = newValue },
            maxLines = 100,
            isSingleLine = false,
            onClearClicked = {
                note = ""
            },
            label = "Note",
            hint = "Write your notes here....."
        )
        Spacer(modifier = modifier.weight(0.2f))

        Button(
            onClick = {
                if (title.isNotEmpty() && note.isNotEmpty()) {
                    sharedViewModel.addNote(title, note)
                    mainNavHostController.navigate(CHOOSE_TOPIC)
                }


            }, colors = ButtonColors(
                containerColor = PrimaryColor,
                contentColor = PrimaryColor,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent,
            ), shape = MaterialTheme.shapes.medium, modifier = modifier.height(50.dp)
        ) {
            Text(
                text = "Next",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 15.sp,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = modifier.weight(0.1f))


    }


}





