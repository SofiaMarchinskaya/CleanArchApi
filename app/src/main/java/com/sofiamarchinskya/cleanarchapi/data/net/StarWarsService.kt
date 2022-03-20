package com.sofiamarchinskya.cleanarchapi.data.net

import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result

interface StarWarsService {

    suspend fun getPersonList(): Result<List<Person>>
}