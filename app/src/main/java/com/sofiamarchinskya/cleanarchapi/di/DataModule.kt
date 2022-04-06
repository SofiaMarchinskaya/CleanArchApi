package com.sofiamarchinskya.cleanarchapi.di

import androidx.room.Room
import com.sofiamarchinskya.cleanarchapi.data.StarWarsRepositoryImpl
import com.sofiamarchinskya.cleanarchapi.data.storage.PersonStorage
import com.sofiamarchinskya.cleanarchapi.data.storage.PersonStorageImpl
import com.sofiamarchinskya.cleanarchapi.data.storage.database.AppDatabase
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<PersonStorage> { PersonStorageImpl(get(), Dispatchers.IO) }
    single <StarWarsRepository>{ StarWarsRepositoryImpl(get(), get()) }

    single {
        Room.databaseBuilder(
            androidContext().applicationContext,
            AppDatabase::class.java,
            "favorite-db"
        ).build()
    }
    single { get<AppDatabase>().favoriteDao }
}