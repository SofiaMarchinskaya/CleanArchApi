package com.sofiamarchinskya.cleanarchapi.data

import androidx.lifecycle.LiveData
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsService
import com.sofiamarchinskya.cleanarchapi.data.storage.PersonStorage
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class StarWarsRepositoryImpl @Inject constructor(
    private val starWarsService: StarWarsService,
    private val storage: PersonStorage,
) : StarWarsRepository {

    override fun observePersonList(): LiveData<Result<List<Person>>> {
        return storage.observePersonList()
    }

    override suspend fun getPersonList(forceUpdate: Boolean): Result<List<Person>> {
        if (forceUpdate) {
            try {
                updateListFromRemoteDataSource()
            } catch (ex: Exception) {
                return Result.Error(ex)
            }
        }
        return storage.getPersonList()
    }

    private suspend fun updateListFromRemoteDataSource() {
        val remote = starWarsService.getPersonList()
        if (remote is Result.Success) {
            remote.data.forEach { person ->
                storage.addPerson(person)
            }
        } else if (remote is Result.Error) {
            throw remote.exception
        }
    }

    override suspend fun refreshPersonList() {
        val res = starWarsService.getPersonList()

        if (res is Result.Success) {
            res.data.forEach { person ->
                storage.addPerson(person)
            }
        } else if (res is Result.Error) {
            throw res.exception
        }
    }

    override suspend fun getPerson(url: String, forceUpdate: Boolean): Result<Person> {
        return storage.getPerson(url)
    }

    override suspend fun makeFavorite(person: Person) {
        coroutineScope {
            launch {
                storage.addFavorites(person)
            }
        }
    }

    override suspend fun deleteFromFavorite(person: Person) {
        coroutineScope {
            launch { storage.deleteFromFavorites(person) }
        }
    }

    override suspend fun clearFavorites() {
        coroutineScope {
            launch { storage.clearFavorites() }
        }
    }

    override fun observePerson(url: String): LiveData<Result<Person>> {
        return storage.observePersonById(url)
    }
}
