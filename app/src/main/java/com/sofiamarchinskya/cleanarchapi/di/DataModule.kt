package com.sofiamarchinskya.cleanarchapi.di

import com.sofiamarchinskya.cleanarchapi.data.StarWarsRepositoryImpl
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsService
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsServiceImpl
import com.sofiamarchinskya.cleanarchapi.data.storage.PersonStorage
import com.sofiamarchinskya.cleanarchapi.data.storage.PersonStorageImpl
import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteDao
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DataModule {
    @Binds
    abstract fun provideStarWarsRepository(
        repositoryImpl: StarWarsRepositoryImpl
    ): StarWarsRepository

    @Binds
    abstract fun provideService(starWarsApi: StarWarsServiceImpl): StarWarsService
}

@Module
class StorageModule {
    @Provides
    fun provideStorage(dao: FavoriteDao): PersonStorage = PersonStorageImpl(dao)
}