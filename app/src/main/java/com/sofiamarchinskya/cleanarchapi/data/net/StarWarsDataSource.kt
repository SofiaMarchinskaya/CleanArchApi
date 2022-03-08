package com.sofiamarchinskya.cleanarchapi.data.net

class StarWarsDataSource (private val service: StarWarsService){
    suspend fun getServerModel() = service.getPersonList()
}