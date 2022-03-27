package com.sofiamarchinskya.cleanarchapi.domain

import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
import kotlinx.coroutines.flow.Flow

interface StarWarsRepository {

    fun observePersonList(): Flow<Result<List<Person>>>

    suspend fun getPersonList(update: Boolean = false): Result<List<Person>>

    suspend fun refreshPersonList()

    suspend fun makeFavorite(person: Person)

    suspend fun deleteFromFavorite(person: Person)

    suspend fun clearFavorites()

    suspend fun getPersonByUrl(url: String):Result<Person>
}