package com.sofiamarchinskya.cleanarchapi.di

import android.content.Context
import com.sofiamarchinskya.cleanarchapi.presentation.view.PeopleListFragment
import com.sofiamarchinskya.cleanarchapi.presentation.view.PersonDetailsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, DataModule::class,
        NetworkModule::class, DomainModule::class, ViewModelModule::class, StorageModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(peopleListFragment: PeopleListFragment)

    fun inject(personDetailsFragment: PersonDetailsFragment)
}