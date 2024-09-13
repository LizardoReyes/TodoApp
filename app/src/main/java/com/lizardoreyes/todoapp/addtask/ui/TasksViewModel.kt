package com.lizardoreyes.todoapp.addtask.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lizardoreyes.todoapp.addtask.domain.GetTasksUseCase
import com.lizardoreyes.todoapp.addtask.domain.InsertTaskUseCase
import com.lizardoreyes.todoapp.addtask.ui.TasksUiState.Success
import com.lizardoreyes.todoapp.addtask.ui.model.TaskModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class TasksViewModel @Inject constructor(
    private val insertTaskUseCase: InsertTaskUseCase,
    getTasksUseCase: GetTasksUseCase,
) : ViewModel() {

    private val uiState: StateFlow<TasksUiState> =
        getTasksUseCase().map(::Success).catch { TasksUiState.Error(it) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TasksUiState.Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _tasks = mutableStateListOf<TaskModel>()
    val tasks: List<TaskModel> = _tasks

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTasksCreated(task: String) {
        _showDialog.value = false
        _tasks.add(TaskModel(task = task))

        viewModelScope.launch {
            insertTaskUseCase(TaskModel(task = task))
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        val index = _tasks.indexOf(taskModel)
        _tasks[index] = _tasks[index].copy(selected = !taskModel.selected)
    }

    fun onItemRemove(taskModel: TaskModel) {
        _tasks.remove(taskModel)
    }
}