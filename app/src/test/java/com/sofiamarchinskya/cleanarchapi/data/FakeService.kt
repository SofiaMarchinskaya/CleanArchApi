package com.sofiamarchinskya.cleanarchapi.data

import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsService

class FakeService(private val list: List<PersonServerModel> = listOf()):StarWarsService {

    override suspend fun getPersonList(): List<PersonServerModel>  = list
}