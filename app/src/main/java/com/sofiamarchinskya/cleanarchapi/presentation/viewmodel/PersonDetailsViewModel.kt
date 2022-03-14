package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsInteractor
import com.sofiamarchinskya.cleanarchapi.presentation.model.UIModel

class PersonDetailsViewModel(private val interactor: StarWarsInteractor) : ViewModel() {

    suspend fun addToFavourites(person: UIModel) {
        interactor.insertFavorites(person.toDomainPersonModel())
    }

    suspend fun deleteFromFav(url: String) {
        interactor.removeFromFavorites(url)
    }
}