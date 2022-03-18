package com.sofiamarchinskya.cleanarchapi.data

import com.sofiamarchinskya.cleanarchapi.data.net.PersonServerModel
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsService

class FakeService:StarWarsService {
    override suspend fun getPersonList(): List<PersonServerModel> {
        TODO("Not yet implemented")
    }
}