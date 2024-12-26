package com.example.to_do_app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do_app.common.UiState
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

    fun fetchTodo(id: Int) {
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
}