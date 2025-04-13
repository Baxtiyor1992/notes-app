package com.example.notesapp.ui.screen.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.PratikFagadiya.smoothanimationbottombar.model.SmoothAnimationBottomBarScreens
import com.PratikFagadiya.smoothanimationbottombar.properties.BottomBarProperties
import com.PratikFagadiya.smoothanimationbottombar.ui.SmoothAnimationBottomBar
import com.example.notesapp.ui.screen.main.home.add.AddNotePage
import com.example.notesapp.ui.screen.main.home.dashboard.DashboardPage
import com.example.notesapp.ui.screen.main.home.topic.TopicPage
import com.example.notesapp.ui.theme.ActiveIndicatorColor
import com.example.notesapp.ui.theme.PrimaryColor
import com.example.notesapp.ui.utill.SEARCH_SCREEN

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    val bottomNavController = rememberNavController()

    val currentIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    val screens = screenList()

    Scaffold(
        bottomBar = {
            Box(modifier = modifier.navigationBarsPadding()) {
                SmoothAnimationBottomBar(
                    navController = bottomNavController,
                    bottomNavigationItems = screens,
                    initialIndex = currentIndex,
                    bottomBarProperties = BottomBarProperties(
                        backgroundColor = PrimaryColor,
                        indicatorColor = ActiveIndicatorColor,
                        iconTintColor = Color.LightGray,
                        iconTintActiveColor = Color.White,
                        textActiveColor = Color.White,
                        cornerRadius = 20.dp,
                    ),
                    onSelectItem = {
                        bottomNavController.navigate(it.route) {
                            popUpTo(bottomNavController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            }
        },
        topBar = {
            TopAppBar {
                navHostController.navigate(route = SEARCH_SCREEN)
            }
        }) { innerPadding ->
        BottomBarNavHost(
            bottomBarNavController = bottomNavController,
            modifier = modifier.padding(innerPadding),
            mainNavController = navHostController
        )
    }
}

fun screenList(): List<SmoothAnimationBottomBarScreens> {
    return listOf(
        SmoothAnimationBottomBarScreens(
            route = BottomBarNavigation.HomePage.route, "Home", BottomBarNavigation.HomePage.icon
        ), SmoothAnimationBottomBarScreens(
            route = BottomBarNavigation.AddPage.route, "Add note", BottomBarNavigation.AddPage.icon
        ), SmoothAnimationBottomBarScreens(
            route = BottomBarNavigation.TopicPage.route, "Topic", BottomBarNavigation.TopicPage.icon
        )
    )
}

@Composable
fun BottomBarNavHost(
    modifier: Modifier = Modifier,
    bottomBarNavController: NavHostController,
    mainNavController: NavHostController
) {
    NavHost(
        startDestination = BottomBarNavigation.HomePage.route,
        navController = bottomBarNavController,
        modifier = modifier
    ) {
        composable(route = BottomBarNavigation.HomePage.route) { DashboardPage(mainNavController = mainNavController) }
        composable(route = BottomBarNavigation.AddPage.route) { AddNotePage() }
        composable(route = BottomBarNavigation.TopicPage.route) { TopicPage() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    onSearchClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "My Notes",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PrimaryColor
        ),
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            }
        },
    )
}