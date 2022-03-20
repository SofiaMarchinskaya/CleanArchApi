package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel


import androidx.lifecycle.ViewModel
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository

class PersonDetailsViewModel(private val repository: StarWarsRepository) : ViewModel() {

    suspend fun addToFavourites(person: Person) {
        repository.makeFavorite(person)
    }

    suspend fun deleteFromFav(person: Person) {
        repository.deleteFromFavorite(person)
    }
}