package org.example.project.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.example.project.ui.screens.AddNoteScreen
import org.example.project.ui.screens.EditNoteScreen
import org.example.project.ui.screens.FavoritesScreen
import org.example.project.ui.screens.NoteDetailScreen
import org.example.project.ui.screens.NotesScreen
import org.example.project.ui.screens.ProfileScreen
import org.example.project.viewmodel.NotesViewModel
import org.example.project.viewmodel.ProfileViewModel

@Composable
fun AppNavigation(
    notesViewModel: NotesViewModel,
    profileViewModel: ProfileViewModel
) {
    val navController = rememberNavController()
    val uiState by notesViewModel.uiState.collectAsState()

    val visibleNotes = notesViewModel.getVisibleNotes()
    val favoriteNotes = notesViewModel.getFavoriteNotes()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val showBottomBar = currentRoute == Screen.Notes.route ||
        currentRoute == Screen.Favorites.route ||
        currentRoute == Screen.Profile.route

    val showFab = currentRoute == Screen.Notes.route

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(navController = navController)
            }
        },
        floatingActionButton = {
            if (showFab) {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.AddNote.route) }
                ) {
                    Text("+")
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Notes.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Notes.route) {
                NotesScreen(
                    notes = visibleNotes,
                    searchQuery = uiState.searchQuery,
                    currentFilterLabel = uiState.notesFilter.name.replace('_', ' '),
                    totalNotes = uiState.notes.size,
                    onSearchQueryChange = notesViewModel::onSearchQueryChange,
                    onCycleSort = notesViewModel::cycleSortMode,
                    onShowAll = { notesViewModel.setFilter(org.example.project.viewmodel.NotesFilter.ALL) },
                    onShowOnlyFavorites = { notesViewModel.setFilter(org.example.project.viewmodel.NotesFilter.ONLY_FAVORITES) },
                    onNoteClick = { noteId ->
                        navController.navigate(Screen.NoteDetail.createRoute(noteId))
                    }
                )
            }

            composable(Screen.Favorites.route) {
                FavoritesScreen(
                    notes = favoriteNotes,
                    onCycleSort = notesViewModel::cycleSortMode,
                    onNoteClick = { noteId ->
                        navController.navigate(Screen.NoteDetail.createRoute(noteId))
                    }
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen(profileViewModel = profileViewModel)
            }

            composable(Screen.AddNote.route) {
                AddNoteScreen(
                    onBack = { navController.popBackStack() },
                    onSave = { title, content, category ->
                        notesViewModel.addNote(title, content, category)
                        navController.popBackStack()
                    }
                )
            }

            composable(
                route = Screen.NoteDetail.route,
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStack ->
                val noteId = backStack.arguments?.getInt("noteId") ?: -1
                val note = notesViewModel.getNoteById(noteId)
                NoteDetailScreen(
                    note = note,
                    onBack = { navController.popBackStack() },
                    onToggleFavorite = { id -> notesViewModel.toggleFavorite(id) },
                    onEdit = { id -> navController.navigate(Screen.EditNote.createRoute(id)) }
                )
            }

            composable(
                route = Screen.EditNote.route,
                arguments = listOf(navArgument("noteId") { type = NavType.IntType })
            ) { backStack ->
                val noteId = backStack.arguments?.getInt("noteId") ?: -1
                val note = notesViewModel.getNoteById(noteId)
                EditNoteScreen(
                    note = note,
                    onBack = { navController.popBackStack() },
                    onSave = { title, content, category ->
                        notesViewModel.updateNote(noteId, title, content, category)
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
