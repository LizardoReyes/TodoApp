package com.lizardoreyes.todoapp.addtask.ui

import com.lizardoreyes.todoapp.addtask.ui.model.TaskModel

sealed interface TaskUiState {
    data object Loading: TaskUiState
    data class Error(val throwable: Throwable): TaskUiState
    data class Success(val tasks: List<TaskModel>): TaskUiState
}