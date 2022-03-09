package com.sofiamarchinskya.cleanarchapi.data.net

import android.accounts.NetworkErrorException

class StarWarsServiceImpl(private val starWarsApi: StarWarsApi) : StarWarsService {

    override suspend fun getPersonList(): List<PersonServerModel> {
        try {
            val response = starWarsApi.getPeople()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { resp ->
                    return resp.results
                }
                throw NetworkErrorException("No Data")
            } else {
                throw Exception(response.errorBody().toString())
            }
        } catch (e: Exception) {
            throw NetworkErrorException("Server error + ${e.message}")
        }
    }
}
