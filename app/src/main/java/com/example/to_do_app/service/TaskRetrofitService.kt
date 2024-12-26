package com.example.to_do_app.service

import com.example.to_do_app.data.TaskDto
import com.example.to_do_app.data.TaskResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskRetrofitService {
    @GET("todos")
    suspend fun getTodos(): TaskResponseDto

    @GET("todos/{id}")
    suspend fun getTodoById(@Path("id") id: Int): TaskDto

    @POST("todos/add")
    suspend fun addTodo(@Body task: TaskDto): TaskDto

    @POST("todos/{id}")
    suspend fun updateTodo(@Path("id") id: Int)

    @DELETE("todos/{id}")
    suspend fun deleteTodo()

}