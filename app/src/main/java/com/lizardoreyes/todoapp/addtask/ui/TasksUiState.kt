package com.lizardoreyes.todoapp.addtask.ui

import com.lizardoreyes.todoapp.addtask.ui.model.TaskModel

sealed interface TasksUiState {
    data object Loading: TasksUiState
    data class Error(val throwable: Throwable): TasksUiState
    data class Success(val tasks: List<TaskModel>): TasksUiState
}