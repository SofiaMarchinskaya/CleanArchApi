package com.sofiamarchinskya.cleanarchapi.data.net

interface StarWarsService {


   suspend fun getPersonList(): List<PersonServerModel>

    fun getWishlist(productIds: List<String>): List<PersonServerModel>
}