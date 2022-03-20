package com.sofiamarchinskya.cleanarchapi.domain

import androidx.lifecycle.LiveData
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result

interface StarWarsRepository {

    fun observePersonList(): LiveData<Result<List<Person>>>

    suspend fun getPersonList(forceUpdate: Boolean = false): Result<List<Person>>

    suspend fun refreshPersonList()

    suspend fun getPerson(url: String, forceUpdate: Boolean = false): Result<Person>

    suspend fun makeFavorite(person: Person)

    suspend fun deleteFromFavorite(person: Person)

    suspend fun clearFavorites()
}