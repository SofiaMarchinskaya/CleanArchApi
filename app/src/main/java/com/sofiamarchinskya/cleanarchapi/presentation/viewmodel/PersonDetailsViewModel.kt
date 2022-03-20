package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel


import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import com.sofiamarchinskya.cleanarchapi.presentation.SingleLiveEvent
import kotlinx.coroutines.launch

class PersonDetailsViewModel(private val repository: StarWarsRepository) : ViewModel() {

    private val _taskId = MutableLiveData<String>()

    val _snackbarText = SingleLiveEvent<Int>()

    private val _task = _taskId.switchMap { taskId ->
        repository.observePerson(taskId).map { computeResult(it) }
    }
    
    val task: LiveData<Person?> = _task

    fun start(taskId: String) {
        // If we're already loading or already loaded, return (might be a config change)
        if (taskId == _taskId.value) {
            return
        }
        // Trigger the load
        _taskId.value = taskId
    }

    private fun computeResult(taskResult: Result<Person>): Person? {
        return if (taskResult is Result.Success) {
            taskResult.data
        } else {
            Log.d("БЫК",taskResult.toString())
            null
        }
    }

    fun addFavorites(isFavorite: Boolean) = viewModelScope.launch {
        val task = _task.value ?: return@launch
        if (isFavorite) {
            repository.makeFavorite(task)
            showSnackbarMessage(R.string.task_marked_complete)
        } else {
            repository.deleteFromFavorite(task)
            showSnackbarMessage(R.string.task_marked_active)
        }
    }

    private fun showSnackbarMessage(@StringRes message: Int) {
        _snackbarText.value = message
    }
}