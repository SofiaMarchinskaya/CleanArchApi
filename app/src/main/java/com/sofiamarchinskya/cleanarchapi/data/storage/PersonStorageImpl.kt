package com.sofiamarchinskya.cleanarchapi.data.storage

import android.util.Log
import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteDao
import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteEntity
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonStorageImpl@Inject constructor(private val dao: FavoriteDao):PersonStorage {

    override fun getAllFavorite(): Flow<List<DomainPersonModel>> {
        return dao.getAllFavorite().map {
            it.map { favoriteEntity ->
                DomainPersonModel(
                    name = favoriteEntity.name,
                    url = favoriteEntity.url,
                    isFavourite = true,
                    height = favoriteEntity.height
                )
            }
        }
    }

    override suspend fun insert(personModel: FavoriteEntity) {
        dao.insertFavorite(personModel)
        Log.d("Бык","данные пришли в хранилище ${personModel.name}")
    }

    override suspend fun getFavoriteByUrl(url: String): FavoriteEntity {
        return dao.getFavoriteByUrl(url)
    }
}