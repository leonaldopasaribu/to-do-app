package com.example.to_do_app.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.to_do_app.common.UiState
import com.example.to_do_app.core.entity.TaskEntity
import com.example.to_do_app.presentation.component.BottomNavBar
import com.example.to_do_app.presentation.component.TaskCard
import com.example.to_do_app.presentation.component.TopAppBar
import com.example.to_do_app.presentation.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val currentRoute = "home"
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(navController, "Tasks") },
        bottomBar = { BottomNavBar(
            navController = navController,
            currentRoute = currentRoute,
            onFabClick = { navController.navigate("addTask") }
        )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            when(uiState){
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                }

                is UiState.Success<*> -> {
                    val todos = (uiState as UiState.Success<List<TaskEntity>>).data
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(todos) { todo ->
                            TaskCard(todo, navController)
                        }
                    }
                }

                is UiState.Error -> {
                    val message = (uiState as UiState.Error).message ?: "An unknown error occurred"
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Error: $message",
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                is UiState.Idle -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No tasks available",
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}
