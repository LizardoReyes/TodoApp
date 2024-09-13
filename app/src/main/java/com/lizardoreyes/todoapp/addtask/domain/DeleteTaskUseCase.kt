package com.lizardoreyes.todoapp.addtask.domain

import com.lizardoreyes.todoapp.addtask.data.TaskRepository
import com.lizardoreyes.todoapp.addtask.ui.model.TaskModel
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(private val repository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel) {
        repository.deleteTask(taskModel)
    }
}