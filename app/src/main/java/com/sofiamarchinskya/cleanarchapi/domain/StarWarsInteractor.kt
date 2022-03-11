package com.sofiamarchinskya.cleanarchapi.domain

import com.sofiamarchinskya.cleanarchapi.presentation.model.UIModel
import kotlinx.coroutines.flow.Flow

interface StarWarsInteractor {

    suspend fun getItemList(): List<UIModel>

    fun getFavoritesList(): Flow<List<UIModel>>

    suspend fun insertFavorites(data: UIModel)

    suspend fun removeFromFavorites(url: String)
}