package com.sofiamarchinskya.cleanarchapi.di

import android.content.Context
import androidx.room.PrimaryKey
import androidx.room.Room
import com.sofiamarchinskya.cleanarchapi.core.domain.CommonInteractor
import com.sofiamarchinskya.cleanarchapi.data.storage.database.AppDatabase
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    fun providePeopleListViewModelFactory(interactor: CommonInteractor): PeopleListViewModelFactory =
        PeopleListViewModelFactory(interactor)

    @Provides
    fun providePersonDetailsViewModelFactory(interactor: CommonInteractor):PersonDetailsViewModelFactory =
        PersonDetailsViewModelFactory(interactor)

    @Provides
    fun provideFavoritesListViewModelFactory(interactor: CommonInteractor):FavoritesListViewModelFactory =
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