package com.lizardoreyes.todoapp.addtask.data

import com.lizardoreyes.todoapp.addtask.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {
    private val tasks: Flow<List<TaskModel>> = taskDao.getAllTasks().map { taskEntities ->
        taskEntities.map { taskEntity ->
            TaskModel(taskEntity.id, taskEntity.task, taskEntity.selected)
        }
    }

    suspend fun insertTask(taskModel: TaskModel) {
        taskDao.insertTask(TaskEntity(taskModel.id, taskModel.task, taskModel.selected))
    }

    suspend fun updateTask(taskModel: TaskModel) {
        taskDao.updateTask(TaskEntity(taskModel.id, taskModel.task, taskModel.selected))
    }

    suspend fun deleteTask(taskModel: TaskModel) {
        taskDao.deleteTask(TaskEntity(taskModel.id, taskModel.task, taskModel.selected))
    }

    suspend fun getTaskById(id: Long): TaskModel {
        return taskDao.getTaskById(id).let { taskEntity ->
            TaskModel(taskEntity.id, taskEntity.task, taskEntity.selected)
        }
    }

    fun getAllTasks(): Flow<List<TaskModel>> {
        return tasks
    }
}