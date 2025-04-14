package com.example.notesapp.ui.screen.main.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.PratikFagadiya.smoothanimationbottombar.model.SmoothAnimationBottomBarScreens
import com.PratikFagadiya.smoothanimationbottombar.properties.BottomBarProperties
import com.PratikFagadiya.smoothanimationbottombar.ui.SmoothAnimationBottomBar
import com.example.notesapp.R
import com.example.notesapp.ui.screen.main.SharedViewModel
import com.example.notesapp.ui.screen.main.home.add.AddNotePage
import com.example.notesapp.ui.screen.main.home.dashboard.DashboardPage
import com.example.notesapp.ui.screen.main.home.topic.TopicPage
import com.example.notesapp.ui.theme.ActiveIndicatorColor
import com.example.notesapp.ui.theme.PrimaryColor
import com.example.notesapp.ui.utill.ADD_PAGE
import com.example.notesapp.ui.utill.HOME_PAGE
import com.example.notesapp.ui.utill.SEARCH_SCREEN
import com.example.notesapp.ui.utill.TOPIC_PAGE


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val bottomNavController = rememberNavController()

    val currentIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    var index by remember { mutableIntStateOf(0) }

    val screens = screenList()

    Scaffold(bottomBar = {
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

                    when (it.route) {
                        HOME_PAGE -> index = 0
                        ADD_PAGE -> index = 1
                        TOPIC_PAGE -> index = 2
                    }

                    bottomNavController.navigate(it.route) {
                        popUpTo(bottomNavController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                })
        }
    }, topBar = {
        CustomTopAppBar(
            endIconClicked = {
                navHostController.navigate(SEARCH_SCREEN)
            },
            startIconClicked = {
                currentIndex.intValue = 0
                index = 0
                bottomNavController.navigate(HOME_PAGE) {
                    popUpTo(bottomNavController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            },
            currentIndex = index,
            startIcon = painterResource(R.drawable.ic_note),
            endIcon = painterResource(R.drawable.ic_search)
        )
    }) { innerPadding ->
        BottomBarNavHost(
            bottomBarNavController = bottomNavController,
            modifier = modifier.padding(innerPadding),
            mainNavController = navHostController,
            sharedViewModel = sharedViewModel
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
    mainNavController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    NavHost(
        startDestination = BottomBarNavigation.HomePage.route,
        navController = bottomBarNavController,
        modifier = modifier
    ) {
        composable(route = BottomBarNavigation.HomePage.route) {
            DashboardPage(
                mainNavController = mainNavController,
                sharedViewModel = sharedViewModel
            )
        }
        composable(route = BottomBarNavigation.AddPage.route) {
            AddNotePage(
                mainNavHostController = mainNavController,
                sharedViewModel = sharedViewModel
            )
        }
        composable(route = BottomBarNavigation.TopicPage.route) {
            TopicPage(
                mainNavController = mainNavController,
                sharedViewModel = sharedViewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    endIconClicked: () -> Unit,
    startIconClicked: () -> Unit,
    startIcon: Painter,
    endIcon: Painter?,
    currentIndex: Int = 0
) {

    var title by remember { mutableStateOf("All Notes") }

    title = when (currentIndex) {
        1 -> "Add Note"
        2 -> "Topics"
        3 -> "Choose Topic"
        4 -> "Note Details"
        5 -> "Topic Notes"
        else -> "Notes"
    }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PrimaryColor
        ),
        navigationIcon = {
            IconButton(onClick = startIconClicked) {
                Icon(
                    painter = startIcon,
                    contentDescription = "Note icon",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = endIconClicked) {
                Icon(
                    painter = endIcon ?: painterResource(
                        R.drawable
                            .ic_statistics
                    ),
                    contentDescription = "Search",
                    tint = if (endIcon == null) Color.Transparent else Color.White
                )
            }
        },
    )
}