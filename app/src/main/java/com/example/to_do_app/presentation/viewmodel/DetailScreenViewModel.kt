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
class DetailScreenViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    val uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Idle)

    fun fetchTask(id: Int) {
        viewModelScope.launch {
            uiState.value = UiState.Loading
            try {
                val todo = taskRepository.fetchTaskById(id)
                uiState.value = UiState.Success(todo)
            } catch (e: Exception) {
                uiState.value = UiState.Error(e.message)
            }
        }
    }

    fun updateTaskStatus(id: Int, isCompleted: Boolean, navController: NavHostController) {
        viewModelScope.launch {
            try {
                val currentTodo = (uiState.value as? UiState.Success<TaskEntity>)?.data
                if (currentTodo != null) {
                    val updatedTodo = currentTodo.copy(isCompleted = isCompleted)
                    taskRepository.updateTask(id, updatedTodo)
                    uiState.value = UiState.Success(updatedTodo)
                    
                    showToast(navController, "Task status updated successfully")
                    navigateToHome(navController)
                }
            } catch (e: Exception) {
                uiState.value = UiState.Error(e.message)

                showToast(navController, e.message ?: "An error occurred")
            }
        }
    }

    fun deleteTask(id: Int, navController: NavHostController) {
        viewModelScope.launch {
            try {
                taskRepository.deleteTask(id)

                showToast(navController, "Task deleted successfully")
                navigateToHome(navController)
            } catch (e: Exception) {
                uiState.value = UiState.Error(e.message)
                Toast.makeText(navController.context, e.message ?: "An error occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun navigateToHome(navController: NavHostController) {
        navController.navigate("home") {
            popUpTo("home") { inclusive = true }
        }
    }

    private fun showToast(navController: NavHostController, message: String) {
        Toast.makeText(navController.context, message, Toast.LENGTH_SHORT).show()
    }
}