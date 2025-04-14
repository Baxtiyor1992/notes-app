package uz.polat.noteappatto.ui.screens.main

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import timber.log.Timber
import uz.polat.noteappatto.R
import uz.polat.noteappatto.ui.components.AddButton
import uz.polat.noteappatto.ui.components.CategoryItem
import uz.polat.noteappatto.ui.components.NoteItem
import uz.polat.noteappatto.ui.components.SearchTextField
import uz.polat.noteappatto.ui.components.SettingsBottomSheet
import uz.polat.noteappatto.ui.components.TopicAddBottomSheet
import uz.polat.noteappatto.ui.theme.isDarkMode
import uz.polat.noteappatto.utils.TOPIC_ADD

class MainScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: MainScreenContracts.ViewModel = getViewModel<MainScreenVM>()
        val state = viewModel.collectAsState()
        MainScreenContent(state, viewModel::onEventDispatcher)
        viewModel.getNotesAndTopics()
    }
}

@Composable
fun MainScreenContent(
    state: State<MainScreenContracts.UIState>,
    onEventDispatcher: (MainScreenContracts.Intent) -> Unit
) {
//    val selected = remember { mutableIntStateOf(0) }
    val context = LocalContext.current
    var isClicked = true
    val coroutineScope = rememberCoroutineScope()


    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isDarkMode
    val backgroundColor = MaterialTheme.colorScheme.background

    SideEffect {
        systemUiController.setStatusBarColor(
            color = backgroundColor,
            darkIcons = useDarkIcons
        )
    }

    if (state.value.isAddTopicSheetOpen) {
        TopicAddBottomSheet(
            onClickSave = { name, color ->
                onEventDispatcher(MainScreenContracts.Intent.OnClickSaveTopic( name,color))
            },
            onDismissRequest = {
                onEventDispatcher(MainScreenContracts.Intent.OnDismissTopicAddBottomSheet)
            }
        )
    }

    if (state.value.isSettingsSheetOpen) {
        SettingsBottomSheet(
            isDarkMode = state.value.isDarkMode,
            currentLang = state.value.currentLang,
            onCheckedChanged = {
                onEventDispatcher.invoke(MainScreenContracts.Intent.OnToggleThemeMode(it))
            },
            onDismissRequest = {
                onEventDispatcher.invoke(MainScreenContracts.Intent.OnDismissSettingsBottomSheet)
            },
            onLanguageChanged = {
                onEventDispatcher.invoke(MainScreenContracts.Intent.LangChange(it))
            }
        )
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 24.dp, start = 16.dp, end = 8.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                SearchTextField(
                    modifier = Modifier.weight(1f),
                    value = state.value.searchText,
                    hint = "Search",
                    onValueChange = {
                        onEventDispatcher.invoke(MainScreenContracts.Intent.SearchTextChange(it))
                    }
                )

                Spacer(modifier = Modifier.width(16.dp))

                IconButton(onClick = {
                    onEventDispatcher.invoke(MainScreenContracts.Intent.OnClickSettingsIcon)
                }) {
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

            }
        },
        floatingActionButton = {
            AddButton(
                modifier = Modifier.padding(8.dp)
            ) {
                if (isClicked) {
                    isClicked = false
                    onEventDispatcher.invoke(MainScreenContracts.Intent.OnClickAddButton)
                    coroutineScope.launch {
                        delay(300)
                        isClicked = true
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            if(state.value.searchText.isEmpty()){
                LazyRow(contentPadding = PaddingValues(start = 24.dp)) {
                    items(
                        state.value.topics.size,
                        key = { state.value.topics[it].id }
                    ) {
                        CategoryItem(
                            topic = state.value.topics[it],
                            isSelected = it == state.value.selectedTopic,

                            ) {
                            if (state.value.topics[it].name == TOPIC_ADD) {
                                Timber.tag("TTT").d("TOPIC_ADD clicked")
                                onEventDispatcher.invoke(
                                    MainScreenContracts.Intent.OnClickAddCategory
                                )
                            } else {
                                onEventDispatcher.invoke(
                                    MainScreenContracts.Intent.OnClickCategory(state.value.topics[it],it)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                }
            }


            Spacer(modifier = Modifier.height(8.dp))

            val notes = remember(state.value.notes) { state.value.notes }

            LazyColumn(
                modifier = Modifier.statusBarsPadding(),
                contentPadding = PaddingValues(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items (
                    items = notes
                ){
                    NoteItem(noteEntity = it) {
                        if (isClicked) {
                            isClicked = false
                            onEventDispatcher.invoke(MainScreenContracts.Intent.OnClickNoteItem(it))
                            coroutineScope.launch {
                                delay(300)
                                isClicked = true
                            }
                        }
                    }
                }
            }
        }
    }

    if (state.value.notes.isEmpty()) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_state))
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LottieAnimation(
                    modifier = Modifier.size(250.dp),
                    composition = composition,
                    iterations = LottieConstants.IterateForever,
                    restartOnPlay = true
                )

                Text(
                    text = stringResource(R.string.create_a_note),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
@Preview
private fun PreviewMainScreen() {
    val state = remember {
        mutableStateOf(
            MainScreenContracts.UIState(
                topics = listOf(

                ),
                notes = emptyList()
            )
        )
    }
    MainScreenContent(state) {}
}


