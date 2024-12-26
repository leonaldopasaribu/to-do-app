package com.example.to_do_app.core.entity

data class TaskEntity(
    val id: Int?,
    val isCompleted: Boolean,
    val title: String,
    val userId: Int
)