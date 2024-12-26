package com.example.to_do_app.common

sealed class UiState {
    data object Loading : UiState()
    data class Success<T>(val data: T) : UiState()
    data class Error(val message: String?) : UiState()
    data object Idle : UiState()
}