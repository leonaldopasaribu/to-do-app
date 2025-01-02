package com.example.to_do_app.presentation.viewmodel

import android.widget.Toast
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

                Toast.makeText(navController.context, "Task added successfully", Toast.LENGTH_SHORT).show()

                navigateToHome(navController)
            } catch (e: Exception) {
                uiState.value = UiState.Error(e.message ?: "An error occured")

                Toast.makeText(navController.context, e.message ?: "An error occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToHome(navController: NavHostController) {
        navController.navigate("home") {
            popUpTo("onboarding") { inclusive = true }
        }
    }
}