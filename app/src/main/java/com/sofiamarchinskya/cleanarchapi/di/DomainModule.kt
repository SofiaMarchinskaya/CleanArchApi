package com.sofiamarchinskya.cleanarchapi.di

import com.sofiamarchinskya.cleanarchapi.domain.Interactor
import com.sofiamarchinskya.cleanarchapi.domain.InteractorImpl
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DomainModule {
    @Binds
    abstract fun provideInteractor(interactorImpl: InteractorImpl): Interactor
}