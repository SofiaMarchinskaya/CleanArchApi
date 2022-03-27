package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PersonDetailsViewModel(private val repository: StarWarsRepository) : ViewModel() {

    private val eventChannel = Channel<PersonDetailsEvent>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()
    private val _person = MutableStateFlow<Person?>(null)
    val person = _person.asStateFlow()

    fun loadPerson(url: String) {
        viewModelScope.launch {
            when (val res = repository.getPersonByUrl(url)) {
                is Result.Success -> {
                    _person.value = res.data
                }
                else -> showSnackbarMessage(R.string.loading_error)
            }
        }
    }

    fun addFavorites(isFavorite: Boolean) = viewModelScope.launch {
        if (isFavorite) {
            person.value?.let { repository.makeFavorite(it)
            Log.d("Бык",it.name)
            }
            showSnackbarMessage(R.string.add_to_favorites)
        } else {
            person.value?.let { repository.deleteFromFavorite(it) }
            showSnackbarMessage(R.string.remove_from_favorite)
        }
    }

    private fun showSnackbarMessage(message: Int) {
        viewModelScope.launch {
            eventChannel.send(PersonDetailsEvent.ShowSnackBar(message))
        }
    }
}

sealed class PersonDetailsEvent {
    data class ShowSnackBar(val res: Int) : PersonDetailsEvent()
}