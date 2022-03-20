package com.sofiamarchinskya.cleanarchapi.data.storage.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sofiamarchinskya.cleanarchapi.data.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun observePeople(): LiveData<List<Person>>

    @Query("SELECT * FROM favorite")
    suspend fun getPeople(): List<Person>

    @Query("SELECT * FROM favorite WHERE url = :url")
    suspend fun getPersonById(url: String):Person?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: Person)

    @Query("UPDATE favorite SET isfavorite = :isFavorite WHERE url = :url")
    suspend fun updateFavorites(url: String, isFavorite: Boolean)

    @Query("DELETE FROM favorite WHERE url = :url")
    suspend fun deletePerson(url: String): Int

    @Query("DELETE FROM favorite WHERE isfavorite = 1")
    suspend fun deleteFavorites(): Int
}