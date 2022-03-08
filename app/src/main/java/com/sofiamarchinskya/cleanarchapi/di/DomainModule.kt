package com.sofiamarchinskya.cleanarchapi.di

import com.sofiamarchinskya.cleanarchapi.core.domain.CommonInteractor
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsInteractor
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideInteractor(repository: StarWarsRepository):CommonInteractor = StarWarsInteractor(repository)
}