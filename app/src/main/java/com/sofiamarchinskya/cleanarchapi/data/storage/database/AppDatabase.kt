package com.sofiamarchinskya.cleanarchapi.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sofiamarchinskya.cleanarchapi.data.Person

@Database(
    entities = [
        Person::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val favoriteDao: FavoriteDao
}