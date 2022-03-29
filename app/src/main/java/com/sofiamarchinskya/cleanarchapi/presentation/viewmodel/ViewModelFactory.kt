@file:Suppress("UNCHECKED_CAST")

package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sofiamarchinskya.cleanarchapi.domain.Interactor
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import javax.inject.Inject

class PeopleListViewModelFactory (val interactor: Interactor) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PeopleListViewModel(interactor) as T
    }
}

class PersonDetailsViewModelFactory(val repository: StarWarsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonDetailsViewModel(repository) as T
    }
}