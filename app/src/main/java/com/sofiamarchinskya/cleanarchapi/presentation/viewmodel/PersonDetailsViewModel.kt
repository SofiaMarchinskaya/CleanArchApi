package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel


import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.Event
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import kotlinx.coroutines.launch

class PersonDetailsViewModel(private val repository: StarWarsRepository) : ViewModel() {

    private val _url = MutableLiveData<String>()

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    private val _person = _url.switchMap { url ->
        repository.observePerson(url).map { computeResult(it) }
    }

    val person: LiveData<Person?> = _person

    fun start(url: String) {
        if (url == _url.value) {
            return
        }
        _url.value = url
    }

    private fun computeResult(res: Result<Person>): Person? {
        return if (res is Result.Success) {
            res.data
        } else {
            null
        }
    }

    fun addFavorites(isFavorite: Boolean) = viewModelScope.launch {
        val person = _person.value ?: return@launch
        if (isFavorite) {
            repository.makeFavorite(person)
            showSnackbarMessage(R.string.add_to_favorites)
        } else {
            repository.deleteFromFavorite(person)
            showSnackbarMessage(R.string.remove_from_favorite)
        }
    }

    private fun showSnackbarMessage(@StringRes message: Int) {
        _snackbarText.value = Event(message)
    }
}