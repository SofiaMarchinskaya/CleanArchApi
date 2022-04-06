package com.sofiamarchinskya.cleanarchapi.di

import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsApi
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsService
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsServiceImpl
import com.sofiamarchinskya.cleanarchapi.utils.Constants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

fun provideStarWarsApi(BASE_URL: String): StarWarsApi {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create()
}

val networkModule = module {
    single { provideStarWarsApi(Constants.BASE_URL) }
    single<StarWarsService> { StarWarsServiceImpl(get()) }
}
