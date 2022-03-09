package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sofiamarchinskya.cleanarchapi.core.domain.BaseInteractor
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel

class PersonDetailsViewModel(private val interactor: BaseInteractor):ViewModel() {
    val isChecked = MutableLiveData<Boolean>()

    suspend fun addToFavourites(person: DomainPersonModel) {
       interactor.insertFavorites(person)
    }


//    fun deleteFromFav(url: String) {
//        repository.delete(url)
//    }

}