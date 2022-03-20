package com.sofiamarchinskya.cleanarchapi.di

import com.sofiamarchinskya.cleanarchapi.data.StarWarsRepositoryImpl
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsApi
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsService
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsServiceImpl
import com.sofiamarchinskya.cleanarchapi.data.storage.PersonStorage
import com.sofiamarchinskya.cleanarchapi.data.storage.PersonStorageImpl
import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteDao
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PeopleListViewModelFactory
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PersonDetailsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideStarWarsRepository(
        service: StarWarsService,
        storage: PersonStorage
    ): StarWarsRepository = StarWarsRepositoryImpl(service, storage)

    @Provides
    fun providePeopleListViewModelFactory(repository: StarWarsRepository): PeopleListViewModelFactory =
        PeopleListViewModelFactory(repository)

    @Provides
    fun providePersonDetailsViewModelFactory(repository: StarWarsRepository): PersonDetailsViewModelFactory =
        PersonDetailsViewModelFactory(repository)

    @Provides
    fun provideService(starWarsApi: StarWarsApi): StarWarsService = StarWarsServiceImpl(starWarsApi)

    @Provides
    fun provideStorage(dao: FavoriteDao): PersonStorage = PersonStorageImpl(dao = dao)
}