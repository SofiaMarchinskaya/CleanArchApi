package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sofiamarchinskya.cleanarchapi.core.domain.CommonInteractor
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel

class PersonDetailsViewModel(private val interactor: CommonInteractor):ViewModel() {
    val isChecked = MutableLiveData<Boolean>()

    suspend fun addToFavourites(person: DomainPersonModel) {
       interactor.insertFavorites(person)
        Log.d("Бык","отправили данные в вьюмодели ${person.name}")
    }


//    fun deleteFromFav(url: String) {
//        repository.delete(url)
//    }

}