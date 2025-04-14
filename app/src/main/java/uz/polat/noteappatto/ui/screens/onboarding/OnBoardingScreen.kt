package uz.polat.noteappatto.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.polat.noteappatto.ui.components.StartButton
import uz.polat.noteappatto.R
import uz.polat.noteappatto.ui.theme.mainFont

class OnBoardingScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: OnBoardingContracts.ViewModel = getViewModel<OnBoardingVM>()
        val state = viewModel.collectAsState()
        OnBoardingScreenContent(state, viewModel::onEventDispatcher)
    }
}

@Composable
fun OnBoardingScreenContent(
    state: State<OnBoardingContracts.UIState>,
    onEventDispatcher: (OnBoardingContracts.Intent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFcefe48))
            .statusBarsPadding(),
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier.weight(0.5f),
                painter = painterResource(id = R.drawable.material_2),
                contentDescription = null,
            )
        }

        Column {

            Column(
                modifier = Modifier
                    .weight(0.8f)
                    .padding(start = 24.dp)

            ) {
                Spacer(modifier = Modifier.weight(0.5f))

                Text(
                    modifier = Modifier.weight(1f),
                    text = "the",
                    fontFamily = mainFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 100.sp,
                    lineHeight = 100.sp
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = "best",
                    fontFamily = mainFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 100.sp,
                    lineHeight = 100.sp
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = "app",
                    fontFamily = mainFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 100.sp,
                    lineHeight = 100.sp
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = "for your",
                    fontFamily = mainFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 100.sp,
                    lineHeight = 100.sp
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = "notes",
                    fontFamily = mainFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 100.sp,
                    lineHeight = 100.sp
                )

                Spacer(modifier = Modifier.weight(0.5f))

            }

            Column(modifier = Modifier.weight(0.2f)) {

            }


        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 24.dp, vertical = 56.dp)
        ) {
            StartButton() { onEventDispatcher.invoke(OnBoardingContracts.Intent.OnClickStart) }
        }

    }
}


@Preview
@Composable
private fun PreviewOnBoardingScreen() {
    val state = remember { mutableStateOf(OnBoardingContracts.UIState()) }
    OnBoardingScreenContent(state) {}
}


