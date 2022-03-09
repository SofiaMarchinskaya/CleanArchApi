package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.sofiamarchinskya.cleanarchapi.core.domain.BaseInteractor
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel

class PersonDetailsViewModel(private val interactor: BaseInteractor) : ViewModel() {
    suspend fun addToFavourites(person: DomainPersonModel) {
        interactor.insertFavorites(person)
    }

    suspend fun deleteFromFav(url: String) {
        interactor.removeFromFavorites(url)
    }
}