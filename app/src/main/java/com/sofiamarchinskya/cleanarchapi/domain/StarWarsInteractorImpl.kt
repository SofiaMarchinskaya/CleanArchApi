package com.sofiamarchinskya.cleanarchapi.domain

import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import com.sofiamarchinskya.cleanarchapi.presentation.model.UIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StarWarsInteractorImpl(private val repository: StarWarsRepository) : StarWarsInteractor {

    override suspend fun getItemList(): List<DomainPersonModel> =
        repository.getPersonList()

    override fun getFavoritesList(): Flow<List<DomainPersonModel>> = repository.getFavoritesList()

    override suspend fun insertFavorites(data: DomainPersonModel) {
        repository.addPersonToFavorite(data)
    }

    override suspend fun removeFromFavorites(url: String) {
        repository.delete(url)
    }
}