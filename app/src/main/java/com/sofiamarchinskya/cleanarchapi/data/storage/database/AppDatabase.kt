package com.sofiamarchinskya.cleanarchapi.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        FavoriteEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val favoriteDao: FavoriteDao
}