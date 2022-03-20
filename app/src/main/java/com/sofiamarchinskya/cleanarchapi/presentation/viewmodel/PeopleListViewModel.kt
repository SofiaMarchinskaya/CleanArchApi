package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import com.sofiamarchinskya.cleanarchapi.presentation.SingleLiveEvent
import com.sofiamarchinskya.cleanarchapi.presentation.view.DELETE_RESULT_OK
import com.sofiamarchinskya.cleanarchapi.presentation.view.EDIT_RESULT_OK
import com.sofiamarchinskya.cleanarchapi.presentation.view.FilterType
import kotlinx.coroutines.flow.switchMap
import kotlinx.coroutines.launch

class PeopleListViewModel(private val repository: StarWarsRepository) : ViewModel() {
    //Список TODO
    private val _forceUpdate = MutableLiveData(false)
    private val _items: LiveData<List<Person>> = _forceUpdate.switchMap { forceUpdate ->
        _dataLoading.value = true
            viewModelScope.launch {
                repository.refreshPersonList()
                _dataLoading.value = false
            }

        repository.observePersonList().switchMap { filterList(it) }
    }

    val items: LiveData<List<Person>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _currentFilteringLabel = MutableLiveData<Int>()
    val currentFilteringLabel: LiveData<Int> = _currentFilteringLabel

    private val _noTasksLabel = MutableLiveData<Int>()
    val noTasksLabel: LiveData<Int> = _noTasksLabel

    private val _noTaskIconRes = MutableLiveData<Int>()
    val noTaskIconRes: LiveData<Int> = _noTaskIconRes

    private val _tasksAddViewVisible = MutableLiveData<Boolean>()
    val tasksAddViewVisible: LiveData<Boolean> = _tasksAddViewVisible

    val _snackbarText = SingleLiveEvent<Int>()

    private var currentFiltering = FilterType.ALL_PEOPLE

    // Not used at the moment
    private val isDataLoadingError = MutableLiveData<Boolean>()

   val _openTaskEvent = SingleLiveEvent<String>()

    private var resultMessageShown: Boolean = false

    // This LiveData depends on another so we can use a transformation.
    val empty: LiveData<Boolean> = Transformations.map(_items) {
        it.isEmpty()
    }

    init {
        // Set initial state
        setFiltering(FilterType.ALL_PEOPLE)
        //  loadTasks(true)
    }

    fun setFiltering(requestType: FilterType) {
        currentFiltering = requestType

        // Depending on the filter type, set the filtering label, icon drawables, etc.
        when (requestType) {
            FilterType.ALL_PEOPLE -> {
                setFilter(
                    R.string.label_all, R.string.no_tasks_all,
                    R.drawable.logo_no_fill, true
                )
            }
           FilterType.FAVORITES -> {
                setFilter(
                    R.string.label_active, R.string.no_tasks_active,
                    R.drawable.ic_check_circle_96dp, false
                )
            }
          FilterType.NOT_FAVORITE -> {
                setFilter(
                    R.string.label_completed, R.string.no_tasks_completed,
                    R.drawable.ic_verified_user_96dp, false
                )
            }
        }
         loadPersonList(false)
    }

    private fun setFilter(
        @StringRes filteringLabelString: Int, @StringRes noTasksLabelString: Int,
        @DrawableRes noTaskIconDrawable: Int, tasksAddVisible: Boolean
    ) {
        _currentFilteringLabel.value = filteringLabelString
        _noTasksLabel.value = noTasksLabelString
        _noTaskIconRes.value = noTaskIconDrawable
        _tasksAddViewVisible.value = tasksAddVisible
    }

    fun clearFavorites() {
        viewModelScope.launch {
           repository.clearFavorites()
            showSnackbarMessage(R.string.completed_tasks_cleared)
        }
    }

    //Удаление или добавление в избранное
    fun addFavorites(person: Person, completed: Boolean) = viewModelScope.launch {
        if (completed) {
            repository.makeFavorite(person)
            showSnackbarMessage(R.string.task_marked_complete)
        } else {
            repository.deleteFromFavorite(person)
            showSnackbarMessage(R.string.task_marked_active)
        }
    }


    //открыть подробную информацию
    fun openTask(taskId: String) {
        _openTaskEvent.value = taskId
        _openTaskEvent.call()
    }

    fun showEditResultMessage(result: Int) {
        if (resultMessageShown) return
        when (result) {
            EDIT_RESULT_OK -> showSnackbarMessage(R.string.successfully_saved_task_message)
            DELETE_RESULT_OK -> showSnackbarMessage(R.string.successfully_deleted_task_message)
        }
        resultMessageShown = true
    }

    private fun showSnackbarMessage(message: Int) {
        _snackbarText.value = message
        _snackbarText.call()
    }

    //Фильтрация элементов списка
    private fun filterList(personListResult: Result<List<Person>>): LiveData<List<Person>> {

        val result = MutableLiveData<List<Person>>()

        if (personListResult is Result.Success) {
            isDataLoadingError.value = false
            viewModelScope.launch {
                result.value = filterItems(personListResult.data, currentFiltering)
            }
        } else {
            result.value = emptyList()
            showSnackbarMessage(R.string.loading_tasks_error)
            isDataLoadingError.value = true
        }

        return result
    }

    fun loadPersonList(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

    private fun filterItems(personList: List<Person>, filteringType: FilterType): List<Person> {
        val listToShow = ArrayList<Person>()
        for (person in personList) {
            when (filteringType) {
                FilterType.ALL_PEOPLE -> listToShow.add(person)
                FilterType.FAVORITES -> if (person.isfavorite) {
                    listToShow.add(person)
                }
                FilterType.NOT_FAVORITE -> if (!person.isfavorite) {
                    listToShow.add(person)
                }
            }
        }
        return listToShow
    }
}
