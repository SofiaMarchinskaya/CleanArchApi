package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PersonDetailsViewModel(private val repository: StarWarsRepository) : ViewModel() {

    private val _url = MutableStateFlow<String?>(null)
    private val eventChannel = Channel<PersonDetailsEvent>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    val person: Flow<Person?> = _url.flatMapLatest { url ->
        repository.observePerson(url!!).map { person -> computeResult(person) }
    }
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
        person.collect { person ->
            person?.let {
                if (isFavorite) {
                    repository.makeFavorite(person)
                    eventChannel.send(PersonDetailsEvent.ShowSnackBar(R.string.add_to_favorites))
                } else {
                    repository.deleteFromFavorite(person)
                    eventChannel.send(PersonDetailsEvent.ShowSnackBar(R.string.remove_from_favorite))
                }
            }
        }
    }
}

sealed class PersonDetailsEvent {
    data class ShowSnackBar(val res: Int) : PersonDetailsEvent()
    data class CheckBoxState(val state: Boolean) : PersonDetailsEvent()
}