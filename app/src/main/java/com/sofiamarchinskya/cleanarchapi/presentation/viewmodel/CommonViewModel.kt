package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.sofiamarchinskya.cleanarchapi.presentation.SingleLiveEvent
import com.sofiamarchinskya.cleanarchapi.presentation.model.UIModel

class CommonViewModel : ViewModel() {
    val onNoteItemClickEvent = SingleLiveEvent<UIModel>()

    fun onAboutItemClicked(note: UIModel) {
        onNoteItemClickEvent.value = note
    }
}