package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsInteractor
import com.sofiamarchinskya.cleanarchapi.presentation.SingleLiveEvent
import com.sofiamarchinskya.cleanarchapi.presentation.model.UIModel
import kotlinx.coroutines.flow.map

class FavoritesListViewModel(interactor: StarWarsInteractor) : ViewModel() {

    val allFavorites: LiveData<List<UIModel>> =
        interactor.getFavoritesList().map { data -> data.map { it.toUIModel() } }.asLiveData()
}
