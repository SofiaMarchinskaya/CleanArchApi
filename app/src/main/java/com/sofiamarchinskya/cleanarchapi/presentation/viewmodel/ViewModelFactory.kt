package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sofiamarchinskya.cleanarchapi.core.domain.BaseInteractor

class PeopleListViewModelFactory(val interactor: BaseInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PeopleListViewModel(interactor) as T
    }
}
class FavoritesListViewModelFactory(val interactor: BaseInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoritesListViewModel(interactor) as T
    }
}
class PersonDetailsViewModelFactory(val interactor: BaseInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonDetailsViewModel(interactor) as T
    }
}