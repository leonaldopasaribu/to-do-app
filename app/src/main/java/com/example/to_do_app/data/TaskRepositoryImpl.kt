package com.example.to_do_app.data

import com.example.to_do_app.core.entity.TaskEntity
import com.example.to_do_app.core.repository.TaskRepository
import com.example.to_do_app.service.TaskRetrofitService
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val apiService: TaskRetrofitService) : TaskRepository {
    override suspend fun fetchTasks(): List<TaskEntity> {
        val response = apiService.getTodos()
        val todos = response.todos.map {TaskMapper.toEntity(it)}
        return todos
    }

    override suspend fun fetchTaskById(id: Int): TaskEntity {
        val todoDto = apiService.getTodoById(id)
        return TaskMapper.toEntity(todoDto)
    }

    override suspend fun createTask(task: TaskEntity) {
        val taskDto = TaskMapper.toDto(task)
        apiService.addTodo(taskDto)
    }
//
//    override suspend fun updateTodo(todo: TodoEntity): TodoEntity {
//        val todoDto = TodoMapper.toDto(todo)
//        val updatedTodoDto = apiService.updateTodo(todoDto)
//        return TodoMapper.toEntity(updatedTodoDto)
//    }
//
//    override suspend fun deleteTodo(id: Int) {
//        apiService.deleteTodo(id)
//    }
}
