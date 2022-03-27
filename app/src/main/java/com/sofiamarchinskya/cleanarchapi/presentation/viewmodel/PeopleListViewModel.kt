package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import com.sofiamarchinskya.cleanarchapi.presentation.view.FilterType
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PeopleListViewModel(
    private val repository: StarWarsRepository
) : ViewModel() {
    private val eventChannel = Channel<PeopleListEvent>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()
    private val update = MutableStateFlow(false)
    val _items: Flow<List<Person>> = update.flatMapLatest { forceUpdate ->
        if (forceUpdate) {
            viewModelScope.launch {
                try {
                    repository.refreshPersonList()
                } catch (e: Exception) {
                    showSnackbarMessage(R.string.loading_error)
                }
            }
        }
        repository.observePersonList().flatMapLatest { filterList(it) }
    }

    private var currentFiltering = FilterType.ALL_PEOPLE

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
        viewModelScope.launch {
            eventChannel.send(PeopleListEvent.ChangeFilteringLabel(filteringLabelString))
        }
    }

    fun clearFavorites() {
        viewModelScope.launch {
            repository.clearFavorites()
            showSnackbarMessage(R.string.clear_favorites)
        }
    }

    fun addFavorites(person: Person, isFavorite: Boolean) = viewModelScope.launch {
        if (isFavorite) {
            repository.makeFavorite(person)
            showSnackbarMessage(R.string.add_to_favorites)
        } else {
            repository.deleteFromFavorite(person)
            showSnackbarMessage(R.string.remove_from_favorite)
        }
    }

    fun openPersonDetails(url: String) {
        viewModelScope.launch {
            eventChannel.send(PeopleListEvent.NavigateToPersonDetails(url))
        }
    }

    private fun showSnackbarMessage(message: Int) {
        viewModelScope.launch {
            eventChannel.send(PeopleListEvent.ShowSnackBar(message))
        }
    }

    private fun filterList(personListResult: Result<List<Person>>): Flow<List<Person>> {
        return if (personListResult is Result.Success) {
            filterItems(personListResult.data, currentFiltering)
        } else {
            showSnackbarMessage(R.string.loading_error)
            flowOf(emptyList())
        }
    }

    private fun filterItems(
        personList: List<Person>,
        filteringType: FilterType
    ): Flow<List<Person>> {
        return when (filteringType) {
            FilterType.ALL_PEOPLE -> flowOf(personList)
            FilterType.FAVORITES -> flowOf(personList.filter { it.isfavorite })
            FilterType.NOT_FAVORITE -> flowOf(personList.filter { !it.isfavorite })
        }
    }
}

sealed class PeopleListEvent {
    data class NavigateToPersonDetails(val url: String) : PeopleListEvent()
    data class ShowSnackBar(val res: Int) : PeopleListEvent()
    data class ChangeFilteringLabel(val res: Int) : PeopleListEvent()
}

