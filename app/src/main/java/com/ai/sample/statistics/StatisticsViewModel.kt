package com.ai.sample.statistics

import androidx.core.widget.ListViewAutoScrollHelper
import androidx.lifecycle.*
import com.ai.sample.data.source.Result
import com.ai.sample.data.source.Task
import com.ai.sample.data.source.TasksRepository
import kotlinx.coroutines.launch

class StatisticsViewModel(private val tasksRepository: TasksRepository) :ViewModel() {

   private val tasks:LiveData<Result<List<Task>>>  = tasksRepository.observeTasks()
   private val _dataLoading = MutableLiveData<Boolean>(false)

   private val stats:LiveData<StatsResult?> = tasks.map {
      if (it is Result.Success){
         getActiveAndCompletedStats(it.data)
      }else{
         null
      }

   }

   val activeTasksPercent = stats.map {
     it?.activeTasksPercent?:0f
   }
   val completedTasksPercent: LiveData<Float> = stats.map { it?.completedTasksPercent ?: 0f }

   val dataLoading:LiveData<Boolean> = _dataLoading

   val error:LiveData<Boolean> = tasks.map { it is Error }

  val empty:LiveData<Boolean> = tasks.map { (it as? Result.Success)?.data.isNullOrEmpty() }

  fun refresh(){
     _dataLoading.value = true
     viewModelScope.launch {
        tasksRepository.refreshTasks()
        _dataLoading.value = false

     }

  }





}