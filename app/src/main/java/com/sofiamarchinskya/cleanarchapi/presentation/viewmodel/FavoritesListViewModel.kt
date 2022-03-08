package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.sofiamarchinskya.cleanarchapi.core.domain.CommonInteractor
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.flow.map

class FavoritesListViewModel(interactor: CommonInteractor) : ViewModel() {
    //private val favoriteListMutable = MutableLiveData<List<DomainPersonModel>>()
    val favoriteListMutable = interactor.getFavoritesList().map {
        it
    }.asLiveData(viewModelScope.coroutineContext)

   // val favoriteList:LiveData<List<DomainPersonModel>> = favoriteListMutable
}