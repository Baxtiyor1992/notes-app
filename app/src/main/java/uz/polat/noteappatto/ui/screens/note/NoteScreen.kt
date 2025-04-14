package uz.polat.noteappatto.ui.screens.note

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import timber.log.Timber
import uz.polat.noteappatto.R
import uz.polat.noteappatto.data.source.local.room.entity.NoteData
import uz.polat.noteappatto.data.source.local.room.entity.NoteEntity
import uz.polat.noteappatto.ui.components.AppTextField
import uz.polat.noteappatto.ui.components.CategoryItem
import uz.polat.noteappatto.ui.components.CustomDialog
import uz.polat.noteappatto.ui.components.ImageView
import uz.polat.noteappatto.ui.components.QuoteText
import uz.polat.noteappatto.ui.components.RoundedActionButton
import uz.polat.noteappatto.ui.components.SaveCancelButton
import uz.polat.noteappatto.ui.components.TopicAddBottomSheet
import uz.polat.noteappatto.ui.theme.isDarkMode
import uz.polat.noteappatto.utils.TOPIC_ADD
import uz.polat.noteappatto.utils.showToast


class NoteScreen(
    private val noteEntity: NoteEntity?,
) : Screen {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val viewModel: NoteScreenContracts.ViewModel = getViewModel<NoteScreenVM>()
        viewModel.init(noteEntity = noteEntity)

        viewModel.collectSideEffect {
            if (it.message.isNotEmpty()) {
                context.showToast(message = it.message)
            }
        }

        val state = viewModel.collectAsState()
        NoteScreenContent(state, noteEntity == null, viewModel::onEventDispatcher)
    }
}

@Composable
fun NoteScreenContent(
    state: State<NoteScreenContracts.UIState>,
    showDeleteButton: Boolean = true,
    onEventDispatcher: (NoteScreenContracts.Intent) -> Unit,
) {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isDarkMode
    val backgroundColor = MaterialTheme.colorScheme.background

    SideEffect {
        systemUiController.setStatusBarColor(
            color = backgroundColor,
            darkIcons = useDarkIcons
        )
    }

    val galleryImageLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            onEventDispatcher.invoke(NoteScreenContracts.Intent.AddImage(uri.toString()))
        }
    }

    if (state.value.isAddTopicBottomSheetOpen) {
        TopicAddBottomSheet(
            onClickSave = { name, color ->
                onEventDispatcher(NoteScreenContracts.Intent.OnClickSaveTopic(name, color))
            },
            onDismissRequest = {
                onEventDispatcher(NoteScreenContracts.Intent.OnDismissClickBottomSheet)
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding(),
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
//            modifier = Modifier.weight(0.1f),
            contentPadding = PaddingValues(start = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                state.value.topics.size,
                key = { state.value.topics[it].id }
            ) {
                val topic = state.value.topics[it]
                CategoryItem(
                    topic = state.value.topics[it],
                    isSelected = state.value.selectedTopics.contains(topic)
                ) {
                    if (state.value.topics[it].name == TOPIC_ADD) {
                        Timber.tag("TTT").d("TOPIC_ADD clicked")
                        onEventDispatcher.invoke(
                            NoteScreenContracts.Intent.OnClickAddCategory
                        )
                    } else {
                        onEventDispatcher.invoke(
                            NoteScreenContracts.Intent.OnClickCategory(state.value.topics[it])
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.7f)
                .padding(16.dp)
                .clip(RoundedCornerShape(30.dp))
                .border(
                    BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
                    RoundedCornerShape(30.dp)
                )
        ) {

            val listState = rememberLazyListState()
            LaunchedEffect(state.value.noteDatas.size) {
                if (state.value.noteDatas.isNotEmpty()) {
                    listState.animateScrollToItem(state.value.noteDatas.lastIndex)
                }
            }

            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                items(
                    state.value.noteDatas.size,
                    key = { it }
                ) { index ->
                    when (val current = state.value.noteDatas[index]) {
                        is NoteData.Title -> {
                            var value by remember { mutableStateOf(current.title) }
                            AppTextField(
                                value = value,
                                placeHolder = stringResource(R.string.title),
                            ) {
                                value = it
                                current.title = it
                            }
                        }

                        is NoteData.Text -> {
                            var value by remember { mutableStateOf(current.text) }
                            AppTextField(
                                modifier = Modifier.imePadding(),
                                value = value,
                                textSize = 24,
                                placeHolder = "your text here..."
                            ) {
                                value = it
                                current.text = it
                            }
                        }

                        is NoteData.Img -> {
                            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                                ImageView(uri = current.uri)
                            }
                        }

                        is NoteData.QuotedText -> {
                            var value by remember { mutableStateOf(current.quote) }
                            QuoteText(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 24.dp),
                                text = value,
                                isFocusable = true
                            ) {
                                value = it
                                current.quote = it
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Row() {
                Spacer(modifier = Modifier.weight(0.5f))

                if (!showDeleteButton) {
                    RoundedActionButton (
                        onClick = {onEventDispatcher(NoteScreenContracts.Intent.DeleteNote)},
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                    Spacer(modifier = Modifier.width(24.dp))
                }

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.onBackground)
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                ) {
                    Icon(
                        modifier = Modifier.clickable { onEventDispatcher(NoteScreenContracts.Intent.AddText) },
                        painter = painterResource(id = R.drawable.ic_text),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background
                    )
                    Spacer(modifier = Modifier.width(24.dp))

                    Icon(
                        modifier = Modifier.clickable { galleryImageLauncher.launch("image/*") },
                        painter = painterResource(id = R.drawable.ic_picture),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background
                    )
                    Spacer(modifier = Modifier.width(24.dp))

                    Icon(
                        modifier = Modifier.clickable {
                            onEventDispatcher.invoke(NoteScreenContracts.Intent.AddQuote)
                        },
                        painter = painterResource(id = R.drawable.ic_quote),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background
                    )
                }

                Spacer(modifier = Modifier.width(24.dp))

                RoundedActionButton (
                    onClick = {onEventDispatcher(NoteScreenContracts.Intent.OnClickRemoveLast)}
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background
                    )
                }

                Spacer(modifier = Modifier.weight(0.5f))
            }

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(12.dp))

            SaveCancelButton(onClickCancel = { onEventDispatcher.invoke(NoteScreenContracts.Intent.OnClickCancel) }) {
                onEventDispatcher.invoke(NoteScreenContracts.Intent.OnClickSave)
            }

            Spacer(
                modifier = Modifier
                    .height(24.dp)
                    .navigationBarsPadding()
            )
        }
    }

    // Dialogs for confirmation
    if (state.value.showRemoveItemDialog)
        CustomDialog(
            title = stringResource(R.string.confirm_remove_last_item),
            onConfirm = {
                onEventDispatcher(NoteScreenContracts.Intent.ConfirmDeleteLastItem)
                onEventDispatcher(NoteScreenContracts.Intent.HideRemoveItemConfirmDialog)
            },
            onDismiss = { onEventDispatcher(NoteScreenContracts.Intent.HideRemoveItemConfirmDialog) }
        )

    if (state.value.showDeleteNoteDialog)
        CustomDialog(
            title = stringResource(R.string.do_you_want_to_delete_note),
            onConfirm = {
                onEventDispatcher(NoteScreenContracts.Intent.ConfirmDeleteNote)
                onEventDispatcher(NoteScreenContracts.Intent.HideDeleteNoteConfirmDialog)
            },
            onDismiss = { onEventDispatcher(NoteScreenContracts.Intent.HideDeleteNoteConfirmDialog) }
        )

    if (state.value.showCancelDialog)
        CustomDialog(
            title = stringResource(R.string.changes_will_not_be_applied),
            onConfirm = {
                onEventDispatcher(NoteScreenContracts.Intent.ConfirmCancel)
                onEventDispatcher(NoteScreenContracts.Intent.HideCancelDialog)
            },
            onDismiss = { onEventDispatcher(NoteScreenContracts.Intent.HideCancelDialog) }
        )
}

@Preview
@Composable
private fun PreviewNoteScreen() {
//    val state = remember {
//        mutableStateOf(NoteScreenContracts.UIState())
//    }
//    NoteScreenContent(state) {}
}