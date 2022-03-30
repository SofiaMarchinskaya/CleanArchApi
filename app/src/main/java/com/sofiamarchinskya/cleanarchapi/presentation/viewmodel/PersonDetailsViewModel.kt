package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
import com.sofiamarchinskya.cleanarchapi.domain.Interactor
import com.sofiamarchinskya.cleanarchapi.presentation.Event
import kotlinx.coroutines.launch

class PersonDetailsViewModel(private val interactor: Interactor) : ViewModel() {

    private val _url = MutableLiveData<String>()

    private val _snackbarText = MutableLiveData<Event<Int>>()
    val snackbarText: LiveData<Event<Int>> = _snackbarText

    private val _person = _url.switchMap { url ->
        interactor.observePerson(url).asLiveData().map { computeResult(it) }
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
        _person.value?.let { interactor.addFavorite(it, isFavorite) }
            ?.let { showSnackbarMessage(it) }
    }

    private fun showSnackbarMessage(@StringRes message: Int) {
        _snackbarText.value = Event(message)
    }
}