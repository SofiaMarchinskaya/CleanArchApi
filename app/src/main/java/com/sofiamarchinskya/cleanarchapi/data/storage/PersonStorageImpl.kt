package com.sofiamarchinskya.cleanarchapi.data.storage

import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteDao
import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteEntity
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonStorageImpl @Inject constructor(private val dao: FavoriteDao) : PersonStorage {

    override fun getAllFavorite(): Flow<List<DomainPersonModel>> {
        return dao.getAllFavorite().map {
            it.map { input ->
                input.toDomainPersonModel()
            }
        }
    }

    override suspend fun insert(personModel: FavoriteEntity) {
        dao.insertFavorite(personModel)
    }

    override suspend fun isFavorite(url: String): Boolean = dao.exists(url)

    override suspend fun delete(url: String) {
        dao.deleteByUrl(url)
    }
}