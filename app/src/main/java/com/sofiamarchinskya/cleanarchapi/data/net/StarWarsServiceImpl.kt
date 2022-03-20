package com.sofiamarchinskya.cleanarchapi.data.net

import android.accounts.NetworkErrorException
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
class StarWarsServiceImpl(private val starWarsApi: StarWarsApi) : StarWarsService {

    override suspend fun getPersonList(): Result<List<Person>> {
        try {
            val response = starWarsApi.getPeople()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { resp ->
                    return Result.Success(resp.results)
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
