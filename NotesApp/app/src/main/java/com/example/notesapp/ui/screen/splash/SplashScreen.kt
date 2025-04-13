package com.example.notesapp.ui.screen.splash

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.notesapp.R
import com.example.notesapp.ui.theme.PrimaryColor
import com.example.notesapp.ui.utill.MAIN_ROUTE
import com.example.notesapp.ui.utill.SPLASH_ROUTE
import kotlinx.coroutines.delay

@Preview
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier, navHostController: NavHostController = rememberNavController()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = PrimaryColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.weight(1.4f))
        LottieAnimation()
        Spacer(modifier = modifier.weight(1.7f))
        AnimatedText()
        Spacer(modifier = modifier.weight(0.5f))
        NavigateToMain(navHostController = navHostController)
    }
}

@Composable
fun AnimatedText(modifier: Modifier = Modifier) {

    var isVisible by remember { mutableStateOf(false) }
    val alphaState by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f, animationSpec = tween(
            durationMillis = 1000, easing = EaseIn
        )
    )

    LaunchedEffect(key1 = Unit) {
        delay(300)
        isVisible = true
    }

    Text(
        text = "Write your daily notes",
        modifier = modifier
            .fillMaxWidth()
            .alpha(alphaState),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 22.sp,
    )

}


@Composable
fun NavigateToMain(
    navHostController: NavHostController
) {
    LaunchedEffect(key1 = Unit) {
        delay(2500)
        navHostController.navigate(MAIN_ROUTE) {
            popUpTo(route = SPLASH_ROUTE) {
                inclusive = true
            }
        }
    }
}

@Composable
fun LottieAnimation() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.splash_sc_anim)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition, iterations = LottieConstants.IterateForever
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp),
        horizontalArrangement = Arrangement.Center,
    ) {

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(300.dp),
            contentScale = ContentScale.Crop
        )
    }

}