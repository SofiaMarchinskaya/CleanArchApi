package com.sofiamarchinskya.cleanarchapi.di

import android.content.Context
import androidx.room.Room
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsInteractor
import com.sofiamarchinskya.cleanarchapi.data.storage.database.AppDatabase
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.FavoritesListViewModelFactory
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PeopleListViewModelFactory
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PersonDetailsViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    fun providePeopleListViewModelFactory(interactor: StarWarsInteractor): PeopleListViewModelFactory =
        PeopleListViewModelFactory(interactor)

    @Provides
    fun providePersonDetailsViewModelFactory(interactor: StarWarsInteractor): PersonDetailsViewModelFactory =
        PersonDetailsViewModelFactory(interactor)

    @Provides
    fun provideFavoritesListViewModelFactory(interactor: StarWarsInteractor): FavoritesListViewModelFactory =
        FavoritesListViewModelFactory(interactor)

    @Provides
    fun providesAppContext() = context

    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "favorite-db").build()

    @Provides
    fun providesToDoDao(database: AppDatabase) = database.favoriteDao
}