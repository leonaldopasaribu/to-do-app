package com.example.to_do_app.core.repository

import com.example.to_do_app.core.entity.TaskEntity

interface TaskRepository{
    suspend fun fetchTasks(): List<TaskEntity>
    suspend fun fetchTaskById(id: Int): TaskEntity
    suspend fun createTask(task: TaskEntity)
    suspend fun updateTask(id: Int, task: TaskEntity)
    suspend fun deleteTask(id: Int)
}
