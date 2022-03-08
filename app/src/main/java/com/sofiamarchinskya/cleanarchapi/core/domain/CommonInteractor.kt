package com.sofiamarchinskya.cleanarchapi.core.domain

import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.flow.Flow

interface CommonInteractor {

  suspend fun getItemList() : List<DomainPersonModel>
  fun getFavoritesList(): Flow<List<DomainPersonModel>>
  suspend fun insertFavorites(data: DomainPersonModel)
}