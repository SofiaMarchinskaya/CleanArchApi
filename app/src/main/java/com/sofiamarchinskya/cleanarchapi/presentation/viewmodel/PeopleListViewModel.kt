package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import com.sofiamarchinskya.cleanarchapi.presentation.SingleLiveEvent
import com.sofiamarchinskya.cleanarchapi.presentation.view.FilterType
import kotlinx.coroutines.launch
import kotlin.Exception

class PeopleListViewModel(private val repository: StarWarsRepository) : ViewModel() {
    private val update = MutableLiveData(false)
    private val _items: LiveData<List<Person>> = update.switchMap { forceUpdate ->
        if (forceUpdate) {
            viewModelScope.launch {
                try {
                    repository.refreshPersonList()
                }catch (e: Exception){
                    showSnackbarMessage(R.string.loading_error)
                }
            }
        }
        repository.observePersonList().switchMap { filterList(it) }
    }
    val items: LiveData<List<Person>> = _items


    private val _currentFilteringLabel = MutableLiveData<Int>()
    val currentFilteringLabel: LiveData<Int> = _currentFilteringLabel

    val snackbarText = SingleLiveEvent<Int>()

    private var currentFiltering = FilterType.ALL_PEOPLE

    private val isDataLoadingError = MutableLiveData<Boolean>()

    val openPersonDetailsEvent = SingleLiveEvent<String>()

    init {
        setFiltering(FilterType.ALL_PEOPLE)
        loadPersonList(true)
    }

    fun setFiltering(requestType: FilterType) {
        currentFiltering = requestType
        when (requestType) {
            FilterType.ALL_PEOPLE -> {
                setFilter(
                    R.string.all_label
                )
            }
            FilterType.FAVORITES -> {
                setFilter(
                    R.string.fav_label
                )
            }
            FilterType.NOT_FAVORITE -> {
                setFilter(
                    R.string.not_fav_label
                )
            }
        }
        loadPersonList(false)
    }

    private fun loadPersonList(forceUpdate: Boolean) {
        update.value = forceUpdate
    }

    private fun setFilter(
        @StringRes filteringLabelString: Int
    ) {
        _currentFilteringLabel.value = filteringLabelString
    }

    fun clearFavorites() {
        viewModelScope.launch {
            repository.clearFavorites()
            showSnackbarMessage(R.string.clear_favorites)
        }
    }

    fun addFavorites(person: Person, completed: Boolean) = viewModelScope.launch {
        if (completed) {
            repository.makeFavorite(person)
            showSnackbarMessage(R.string.add_to_favorites)
        } else {
            repository.deleteFromFavorite(person)
            showSnackbarMessage(R.string.remove_from_favorite)
        }
    }

    fun openPersonDetails(taskId: String) {
        openPersonDetailsEvent.value = taskId
    }

    private fun showSnackbarMessage(message: Int) {
        snackbarText.value = message
    }

    private fun filterList(personListResult: Result<List<Person>>): LiveData<List<Person>> {

        val result = MutableLiveData<List<Person>>()

        if (personListResult is Result.Success) {
            isDataLoadingError.value = false
            viewModelScope.launch {
                result.value = filterItems(personListResult.data, currentFiltering)
            }
        } else {
            result.value = emptyList()
            showSnackbarMessage(R.string.loading_error)
            isDataLoadingError.value = true
        }

        return result
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
