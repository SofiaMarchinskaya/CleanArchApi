package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsInteractor
import com.sofiamarchinskya.cleanarchapi.presentation.SingleLiveEvent
import com.sofiamarchinskya.cleanarchapi.presentation.model.UIModel
import kotlinx.coroutines.launch

class PeopleListViewModel(private val interactor: StarWarsInteractor) : ViewModel() {
    val onNoteItemClickEvent = SingleLiveEvent<UIModel>()

    fun onAboutItemClicked(note: UIModel) {
        onNoteItemClickEvent.value = note
    }

    private val personListMutable = MutableLiveData<List<UIModel>>()
    val personList: LiveData<List<UIModel>> = personListMutable

    fun getList() {
        viewModelScope.launch {
            personListMutable.postValue(interactor.getItemList().map { it.toUIModel() })
        }
    }
}
