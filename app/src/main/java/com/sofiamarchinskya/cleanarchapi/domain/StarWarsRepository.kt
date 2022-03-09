package com.sofiamarchinskya.cleanarchapi.domain

import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface StarWarsRepository {

    suspend fun getPersonList(): List<DomainPersonModel>

    fun getFavoritesList(): Flow<List<DomainPersonModel>>

    suspend fun addPersonToFavorite(personModel: DomainPersonModel)

    suspend fun delete(url: String)
}