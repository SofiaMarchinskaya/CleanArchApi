package com.sofiamarchinskya.cleanarchapi.di

import com.sofiamarchinskya.cleanarchapi.domain.Interactor
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PeopleListViewModelFactory
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PersonDetailsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun providePeopleListViewModelFactory(interactor: Interactor): PeopleListViewModelFactory =
        PeopleListViewModelFactory(interactor)

    @Provides
    fun providePersonDetailsViewModelFactory(interactor: Interactor): PersonDetailsViewModelFactory =
        PersonDetailsViewModelFactory(interactor)
}