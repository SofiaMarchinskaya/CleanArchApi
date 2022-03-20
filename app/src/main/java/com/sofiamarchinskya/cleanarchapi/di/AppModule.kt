package com.sofiamarchinskya.cleanarchapi.di

import android.content.Context
import androidx.room.Room
import com.sofiamarchinskya.cleanarchapi.data.storage.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    @Provides
    fun providesAppContext() = context

    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "favorite-db").build()

    @Provides
    fun providesToDoDao(database: AppDatabase) = database.favoriteDao
}