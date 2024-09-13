package com.lizardoreyes.todoapp.addtask.domain

import com.lizardoreyes.todoapp.addtask.data.TaskRepository
import com.lizardoreyes.todoapp.addtask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(private val repository: TaskRepository) {
    operator fun invoke(): Flow<List<TaskModel>> {
        return repository.getAllTasks()
    }
}