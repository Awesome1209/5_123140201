package org.example.project.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

private data class BottomItem(
    val route: String,
    val label: String,
    val iconText: String
)

@Composable
fun BottomNavBar(
    navController: NavHostController
) {
    val items = listOf(
        BottomItem(Screen.Notes.route, "Notes", "N"),
        BottomItem(Screen.Favorites.route, "Favorites", "F"),
        BottomItem(Screen.Profile.route, "Profile", "P")
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Text(item.iconText) },
                label = { Text(item.label) }
            )
        }
    }
}
