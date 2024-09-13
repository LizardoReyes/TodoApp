package com.lizardoreyes.todoapp.addtask.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "task")
    val task: String,

    @ColumnInfo(name = "selected")
    var selected: Boolean = false
)