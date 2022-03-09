package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.lifecycle.*
import com.sofiamarchinskya.cleanarchapi.core.domain.BaseInteractor
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel

class FavoritesListViewModel(interactor: BaseInteractor) : ViewModel() {

    val allFavorites: LiveData<List<DomainPersonModel>> = interactor.getFavoritesList().asLiveData()
}
