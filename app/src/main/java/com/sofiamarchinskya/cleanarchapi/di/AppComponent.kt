package com.sofiamarchinskya.cleanarchapi.di

import com.sofiamarchinskya.cleanarchapi.presentation.view.FavoritesListFragment
import com.sofiamarchinskya.cleanarchapi.presentation.view.PeopleListFragment
import com.sofiamarchinskya.cleanarchapi.presentation.view.PersonDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,DataModule::class,DomainModule::class,NetworkModule::class])
interface AppComponent {

    fun inject(peopleListFragment: PeopleListFragment)

    fun inject(favoritesListFragment: FavoritesListFragment)

    fun inject(personDetailsFragment: PersonDetailsFragment)
}