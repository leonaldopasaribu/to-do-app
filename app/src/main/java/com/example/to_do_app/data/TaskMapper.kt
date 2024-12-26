package com.example.to_do_app.data

import com.example.to_do_app.core.entity.TaskEntity

object TaskMapper {
    fun toEntity(dto: TaskDto): TaskEntity {
        return TaskEntity(
            id = dto.id,
            isCompleted = dto.completed,
            title = dto.todo,
            userId = dto.userId
        )
    }

    fun toDto(entity: TaskEntity): TaskDto {
        return TaskDto(
            id = entity.id,
            todo = entity.title,
            completed = entity.isCompleted,
            userId = entity.userId
        )
    }

}