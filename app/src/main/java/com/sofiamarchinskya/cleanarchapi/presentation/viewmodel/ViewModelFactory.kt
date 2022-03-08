package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sofiamarchinskya.cleanarchapi.core.domain.CommonInteractor
import com.sofiamarchinskya.cleanarchapi.core.domain.MapperImpl
import com.sofiamarchinskya.cleanarchapi.data.PersonPreferencesImpl
import com.sofiamarchinskya.cleanarchapi.data.StarWarsRepositoryImpl
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsServiceImpl
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsInteractor

class PeopleListViewModelFactory(val interactor: CommonInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PeopleListViewModel(interactor) as T
    }
}
class FavoritesListViewModelFactory(val interactor: CommonInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoritesListViewModel(interactor) as T
    }
}
class PersonDetailsViewModelFactory(val interactor: CommonInteractor) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonDetailsViewModel(interactor) as T
    }
}