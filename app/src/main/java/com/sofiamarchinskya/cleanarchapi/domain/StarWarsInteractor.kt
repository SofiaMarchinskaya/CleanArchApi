package com.sofiamarchinskya.cleanarchapi.domain

import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import com.sofiamarchinskya.cleanarchapi.presentation.model.UIModel
import kotlinx.coroutines.flow.Flow

interface StarWarsInteractor {

    suspend fun getItemList(): List<DomainPersonModel>

    fun getFavoritesList(): Flow<List<DomainPersonModel>>

    suspend fun insertFavorites(data: DomainPersonModel)

    suspend fun removeFromFavorites(url: String)
}