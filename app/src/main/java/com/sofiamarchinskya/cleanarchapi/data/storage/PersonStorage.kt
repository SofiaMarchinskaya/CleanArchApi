package com.sofiamarchinskya.cleanarchapi.data.storage

import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteEntity
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.flow.Flow

interface PersonStorage {

    fun getAllFavorite(): Flow<List<DomainPersonModel>>

    suspend fun insert(personModel: FavoriteEntity)
}