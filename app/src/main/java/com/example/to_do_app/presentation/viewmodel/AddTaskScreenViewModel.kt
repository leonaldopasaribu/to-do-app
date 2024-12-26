package com.example.to_do_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.to_do_app.common.UiState
import com.example.to_do_app.core.entity.TaskEntity
import com.example.to_do_app.core.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskScreenViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
) : ViewModel() {
    val uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Idle)

    fun addTask(taskTitle: String, navController: NavHostController) {
        viewModelScope.launch {
            uiState.value = UiState.Loading
            try {
                val task = TaskEntity(title = taskTitle, isCompleted = false, userId = 5, id= null)
                taskRepository.createTask(task)
                uiState.value = UiState.Success("Task added successfully")

                navigateToHome(navController)
            } catch (e: Exception) {
                uiState.value = UiState.Error(e.message ?: "An error occured")
            }
        }
    }

    private fun navigateToHome(navController: NavHostController) {
        navController.navigate("home") {
            popUpTo("onboarding") { inclusive = true }
        }
    }
}