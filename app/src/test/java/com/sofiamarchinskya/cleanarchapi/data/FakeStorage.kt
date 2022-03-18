package com.sofiamarchinskya.cleanarchapi.data

import com.sofiamarchinskya.cleanarchapi.data.storage.PersonStorage
import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteEntity
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeStorage(var data: MutableList<FavoriteEntity>? = mutableListOf()):PersonStorage {

    override fun getAllFavorite(): Flow<List<DomainPersonModel>> = flow{
       emit(ArrayList(data).map { it.toDomainPersonModel() })
    }

    override suspend fun insert(personModel: FavoriteEntity) {
       data?.add(personModel)
    }

    override suspend fun isFavorite(url: String): Boolean  = ArrayList(data).any{it.url==url}

    override suspend fun delete(url: String) {
        data?.removeIf { it.url==url }
    }
}