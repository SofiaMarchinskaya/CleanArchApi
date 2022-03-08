package com.sofiamarchinskya.cleanarchapi.di

import com.sofiamarchinskya.cleanarchapi.core.domain.Mapper
import com.sofiamarchinskya.cleanarchapi.core.domain.MapperImpl
import com.sofiamarchinskya.cleanarchapi.data.DataPerson
import com.sofiamarchinskya.cleanarchapi.data.PersonPreferences
import com.sofiamarchinskya.cleanarchapi.data.PersonPreferencesImpl
import com.sofiamarchinskya.cleanarchapi.data.StarWarsRepositoryImpl
import com.sofiamarchinskya.cleanarchapi.data.net.PersonServerModel
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsApi
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsService
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsServiceImpl
import com.sofiamarchinskya.cleanarchapi.data.storage.PersonStorage
import com.sofiamarchinskya.cleanarchapi.data.storage.PersonStorageImpl
import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteDao
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsInteractor
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideStarWarsRepository(
        service: StarWarsService,
        mapper: Mapper<DataPerson, DomainPersonModel>,
        preferences: PersonPreferences,
        storage: PersonStorage
    ): StarWarsRepository = StarWarsRepositoryImpl(service, mapper, preferences,storage)

    @Provides
    fun provideService(starWarsApi: StarWarsApi): StarWarsService = StarWarsServiceImpl(starWarsApi)

    @Provides
    fun provideMapper(): Mapper<DataPerson, DomainPersonModel> = MapperImpl()

    @Provides
    fun providePreferences(): PersonPreferences = PersonPreferencesImpl()

    @Provides
    fun provideStorage(dao: FavoriteDao): PersonStorage = PersonStorageImpl(dao = dao)
}