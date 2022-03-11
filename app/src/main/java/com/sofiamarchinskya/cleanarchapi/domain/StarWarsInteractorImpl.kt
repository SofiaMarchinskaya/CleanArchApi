package com.sofiamarchinskya.cleanarchapi.domain

import com.sofiamarchinskya.cleanarchapi.presentation.model.UIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StarWarsInteractorImpl(private val repository: StarWarsRepository) : StarWarsInteractor {

    override suspend fun getItemList(): List<UIModel> =
        repository.getPersonList().map { it.toUIModel() }

    override fun getFavoritesList(): Flow<List<UIModel>> = repository.getFavoritesList()
        .map { data -> data.map { it.toUIModel() } }

    override suspend fun insertFavorites(data: UIModel) {
        repository.addPersonToFavorite(data.toDomainPersonModel())
    }

    override suspend fun removeFromFavorites(url: String) {
        repository.delete(url)
    }
}