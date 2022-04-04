package com.sofiamarchinskya.cleanarchapi.di

import android.content.Context
import androidx.room.Room
import com.sofiamarchinskya.cleanarchapi.data.storage.database.AppDatabase
import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule{
    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "favorite-db").build()

    @Provides
    fun providesToDoDao(database: AppDatabase): FavoriteDao = database.favoriteDao
}