package com.example.submissioncompose.navigation

import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen (val route : String){
    object Home : Screen("home")
    object About : Screen("about")
}

data class NavigationItems(
    val title : String,
    val icon : ImageVector,
    val screen: Screen
)