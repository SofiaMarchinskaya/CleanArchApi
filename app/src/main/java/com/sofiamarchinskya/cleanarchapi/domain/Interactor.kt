package com.sofiamarchinskya.cleanarchapi.domain

import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
import kotlinx.coroutines.flow.Flow

interface Interactor {
    fun observePersonList(): Flow<Result<List<Person>>>

    suspend fun getPersonList(update: Boolean = false): Result<List<Person>>

    suspend fun refreshPersonList()

    suspend fun addFavorite(person: Person, isFavorite: Boolean):Int

    suspend fun clearFavorites()

    fun observePerson(url: String): Flow<Result<Person>>
}