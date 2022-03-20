@file:Suppress("UNCHECKED_CAST")

package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import javax.inject.Inject

class PeopleListViewModelFactory @Inject constructor(val repository: StarWarsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PeopleListViewModel(repository) as T
    }
}

class PersonDetailsViewModelFactory@Inject constructor(val repository: StarWarsRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonDetailsViewModel(repository) as T
    }
}