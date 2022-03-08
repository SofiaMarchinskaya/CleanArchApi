package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofiamarchinskya.cleanarchapi.core.domain.CommonInteractor
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PeopleListViewModel(private val interactor: CommonInteractor) : ViewModel() {
   private val personListMutable = MutableLiveData<List<DomainPersonModel>>()
    val personList: LiveData<List<DomainPersonModel>> = personListMutable

    fun getList() {
        viewModelScope.launch {
            personListMutable.postValue(interactor.getItemList())
        }
    }
}