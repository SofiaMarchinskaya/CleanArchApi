package com.sofiamarchinskya.cleanarchapi.di

import com.sofiamarchinskya.cleanarchapi.presentation.view.PeopleListFragment
import com.sofiamarchinskya.cleanarchapi.presentation.view.PersonDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,DataModule::class,NetworkModule::class,DomainModule::class])
interface AppComponent {

    fun inject(peopleListFragment: PeopleListFragment)

    fun inject(personDetailsFragment: PersonDetailsFragment)
}