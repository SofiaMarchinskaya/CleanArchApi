package com.sofiamarchinskya.cleanarchapi.data.storage

import androidx.lifecycle.LiveData
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result

interface PersonStorage {

    fun observePersonList(): LiveData<Result<List<Person>>>

    suspend fun getPersonList(): Result<List<Person>>

    suspend fun addFavorites(person: Person)

    suspend fun deleteFromFavorites(person: Person)

    fun observePersonById(url: String): LiveData<Result<Person>>

    suspend fun clearFavorites()

    suspend fun addPerson(person: Person)
}