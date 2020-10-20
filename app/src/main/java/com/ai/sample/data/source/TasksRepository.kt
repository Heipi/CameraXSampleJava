package com.ai.sample.data.source

import androidx.lifecycle.LiveData

interface TasksRepository {

    fun observeTasks():LiveData<Task>

    suspend fun refreshTasks()
    suspend fun refreshTask(taskId:String)
    suspend fun saveTask(task: Task)
    suspend fun completeTask(task: Task)
    suspend fun completeTask(taskId: String)
    suspend fun activateTask(task: Task)

    suspend fun activateTask(taskId: String)
    suspend fun clearCompletedTasks()
    suspend fun deleteAllTasks()
    suspend fun deleteTask(taskId: String)




}