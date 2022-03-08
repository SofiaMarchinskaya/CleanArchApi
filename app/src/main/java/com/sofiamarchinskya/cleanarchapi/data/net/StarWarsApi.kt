package com.sofiamarchinskya.cleanarchapi.data.net


import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET


interface StarWarsApi{
    @GET("people")
    suspend fun getPeople():Response<PeopleResponse>
}