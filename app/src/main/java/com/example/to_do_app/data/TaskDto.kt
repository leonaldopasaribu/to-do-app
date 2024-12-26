package com.example.to_do_app.data

data class TaskDto(
    val id: Int?,
    val todo: String,
    val completed: Boolean,
    val userId: Int
)

data class TaskResponseDto(
    val todos: List<TaskDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
