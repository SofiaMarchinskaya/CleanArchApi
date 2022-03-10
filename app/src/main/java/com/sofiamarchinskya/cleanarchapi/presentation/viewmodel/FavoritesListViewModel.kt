package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sofiamarchinskya.cleanarchapi.core.domain.BaseInteractor
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import com.sofiamarchinskya.cleanarchapi.presentation.SingleLiveEvent
import com.sofiamarchinskya.cleanarchapi.presentation.model.UIModel

class FavoritesListViewModel(interactor: BaseInteractor) : ViewModel() {
    val onNoteItemClickEvent = SingleLiveEvent<UIModel>()
    val allFavorites: LiveData<List<UIModel>> = interactor.getFavoritesList().asLiveData()

    fun onAboutItemClicked(note: UIModel) {
        onNoteItemClickEvent.value = note
    }
}
