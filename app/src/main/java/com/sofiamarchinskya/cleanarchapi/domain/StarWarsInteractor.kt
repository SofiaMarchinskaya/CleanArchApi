package com.sofiamarchinskya.cleanarchapi.domain

import android.util.Log
import com.sofiamarchinskya.cleanarchapi.core.domain.BaseInteractor
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.flow.Flow

class StarWarsInteractor(private val repository: StarWarsRepository) : BaseInteractor {

    override suspend fun getItemList(): List<DomainPersonModel> = repository.getPersonList()

    override fun getFavoritesList(): Flow<List<DomainPersonModel>> = repository.getFavoritesList()

    override suspend fun insertFavorites(data: DomainPersonModel) {
        Log.d("Бык","данные пришли в интерактор  ${data.name}")
        repository.addPersonToFavorite(data)
    }

    override suspend fun getFavorite(url: String): DomainPersonModel {
        return repository.getFavoriteByUrl(url)
    }
}