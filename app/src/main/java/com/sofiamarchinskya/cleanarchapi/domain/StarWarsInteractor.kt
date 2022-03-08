package com.sofiamarchinskya.cleanarchapi.domain

import com.sofiamarchinskya.cleanarchapi.core.domain.CommonInteractor
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.flow.Flow

class StarWarsInteractor(private val repository: StarWarsRepository) : CommonInteractor {

    override suspend fun getItemList(): List<DomainPersonModel> = repository.getPersonList()

    override fun getFavoritesList(): Flow<List<DomainPersonModel>> = repository.getFavoritesList()

    override suspend fun insertFavorites(data: DomainPersonModel) {
        repository.addPersonToFavorite(data)
    }
}