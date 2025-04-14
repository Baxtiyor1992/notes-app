package uz.polat.noteappatto.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import uz.polat.noteappatto.ui.theme.mainFont
import uz.polat.noteappatto.ui.theme.plainGreyColor
import uz.polat.noteappatto.utils.TOPIC_COLORS

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SettingsBottomSheet(
    onDismissRequest: () -> Unit,
    onClickSave: (String, Color) -> Unit,
) {

    ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = onDismissRequest,
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
        ) {

            Text(
                text = "Settings",
                fontFamily = mainFont,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Start),
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(16.dp))



            Spacer(modifier = Modifier.height(16.dp))



            Spacer(modifier = Modifier.height(16.dp))




        }


    }
}


@Preview
@Composable
private fun AppPreview() {

}