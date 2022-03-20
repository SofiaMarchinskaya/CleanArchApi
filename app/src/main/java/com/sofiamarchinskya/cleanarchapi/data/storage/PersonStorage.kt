package com.sofiamarchinskya.cleanarchapi.data.storage

import androidx.lifecycle.LiveData
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
import kotlinx.coroutines.flow.Flow

interface PersonStorage {

    fun observePersonList(): LiveData<Result<List<Person>>>

    suspend fun getPersonList(): Result<List<Person>>

    suspend fun getPerson(url: String): Result<Person>

    suspend fun addFavorites(person: Person)

    suspend fun deleteFromFavorites(person: Person)

    suspend fun clearFavorites()

    suspend fun addPerson(person: Person)

    suspend fun deletePerson(url: String)
}