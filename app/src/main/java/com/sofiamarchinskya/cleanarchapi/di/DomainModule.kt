package com.sofiamarchinskya.cleanarchapi.di

import com.sofiamarchinskya.cleanarchapi.domain.Interactor
import com.sofiamarchinskya.cleanarchapi.domain.InteractorImpl
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideInteractor(repository: StarWarsRepository): Interactor = InteractorImpl(repository)
}