@file:Suppress("UNCHECKED_CAST")

package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsInteractor

class PeopleListViewModelFactory(val interactor: StarWarsInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PeopleListViewModel(interactor) as T
    }
}

class FavoritesListViewModelFactory(val interactor: StarWarsInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoritesListViewModel(interactor) as T
    }
}

class PersonDetailsViewModelFactory(val interactor: StarWarsInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonDetailsViewModel(interactor) as T
    }
}