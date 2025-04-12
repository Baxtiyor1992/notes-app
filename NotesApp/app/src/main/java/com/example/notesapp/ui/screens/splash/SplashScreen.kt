package com.example.notesapp.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.notesapp.R
import com.example.notesapp.core.constants.MAIN_ROUTE
import com.example.notesapp.core.utill.TypingText
import com.example.notesapp.ui.theme.PrimaryColor
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
        Spacer(modifier = modifier.weight(1.3f))
        LottieAnimation()
        Spacer(modifier = modifier.weight(1.7f))
        TypingText(text = "Write your daily notes", speed = 70)
        Spacer(modifier = modifier.weight(0.5f))
        NavigateToMain(navHostController = navHostController)
    }
}


@Composable
fun NavigateToMain(
    navHostController: NavHostController
) {
    LaunchedEffect(key1 = Unit) {
        delay(2000)
        navHostController.navigate(MAIN_ROUTE)
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
            modifier = Modifier.size(350.dp),
            contentScale = ContentScale.Crop
        )
    }

}