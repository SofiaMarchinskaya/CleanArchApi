package com.sofiamarchinskya.cleanarchapi.data.storage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PersonStorageImpl @Inject constructor(
    private val dao: FavoriteDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PersonStorage {

    override fun observePersonList(): LiveData<Result<List<Person>>> {
        return dao.observePeople().map {
            Result.Success(it)
        }
    }

    override suspend fun getPersonList(): Result<List<Person>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(dao.getPeople())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getPerson(url: String): Result<Person> = withContext(ioDispatcher) {
        try {
            val person = dao.getPersonById(url)
            if (person != null) {
                return@withContext Result.Success(person)
            } else {
                return@withContext Result.Error(Exception("Information not found!"))
            }
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }
    }

    override suspend fun addPerson(person: Person) = withContext(ioDispatcher) {
        dao.insertPerson(person)
    }

    override suspend fun addFavorites(person: Person) {
        withContext(ioDispatcher) {
            dao.updateFavorites(person.url, true)
            Log.d("БЫК", "stor ${person.url}")
        }
    }

    override suspend fun deleteFromFavorites(person: Person) = withContext(ioDispatcher) {
        dao.updateFavorites(person.url, false)
    }

    override fun observePersonById(url: String): LiveData<Result<Person>> {
        return dao.observePersonById(url).map {
            Result.Success(it)
        }
    }

    override suspend fun clearFavorites() = withContext<Unit>(ioDispatcher) {
        dao.deleteFavorites()
    }
}