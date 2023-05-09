package com.example.submissioncompose.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.submissioncompose.R
import com.example.submissioncompose.navigation.NavigationItems
import com.example.submissioncompose.navigation.Screen
import com.example.submissioncompose.ui.theme.SubmissionComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetAnimalApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    Scaffold (bottomBar = {
        BottomBar(navController)
    },
    modifier = modifier){
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    modifier = modifier
                )
            }
            composable(Screen.About.route) {
//                CartScreen()
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
){
    val navigationItems = listOf(
        NavigationItems(
            title = stringResource(R.string.menu_home),
            icon = Icons.Default.Home,
            screen = Screen.Home
        ),
        NavigationItems(
            title = stringResource(R.string.menu_about),
            icon = Icons.Default.Info,
            screen = Screen.About
        ),
    )
    NavigationBar(
        modifier = modifier
    ) {
        navigationItems.map { items ->
            NavigationBarItem(
                icon = {
                       Icon(
                           imageVector = items.icon,
                           contentDescription = items.title
                       )
                },
                label = { Text(items.title)},
                selected = true,
                onClick = {
                    navController.navigate(items.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetAnimalAppPreview(){
    SubmissionComposeTheme {
        JetAnimalApp()
    }
}

@Preview(showBackground = true)
@Composable
fun AnimalListItemPreview(){
    SubmissionComposeTheme {
        AnimalListItem(
            name = "Kuda",
            photoUrl = "",
            description = "Kuda terbang")
    }
}