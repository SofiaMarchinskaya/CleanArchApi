package com.sofiamarchinskya.cleanarchapi.data.net

import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
import javax.inject.Inject

class StarWarsServiceImpl @Inject constructor(private val starWarsApi: StarWarsApi) :
    StarWarsService {

    override suspend fun getPersonList(): Result<List<Person>> {
        try {
            val response = starWarsApi.getPeople()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { resp ->
                    return Result.Success(resp.results)
                }
                return Result.Error(Exception("No data"))
            } else {
                return Result.Error(Exception((response.errorBody().toString())))
            }
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}
