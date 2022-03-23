package com.sofiamarchinskya.cleanarchapi.data

import androidx.lifecycle.LiveData
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsService
import com.sofiamarchinskya.cleanarchapi.data.storage.PersonStorage
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import javax.inject.Inject

class StarWarsRepositoryImpl @Inject constructor(
    private val starWarsService: StarWarsService,
    private val storage: PersonStorage
) : StarWarsRepository {

    override fun observePersonList(): LiveData<Result<List<Person>>> {
        return storage.observePersonList()
    }

    override suspend fun getPersonList(forceUpdate: Boolean): Result<List<Person>> {
        if (forceUpdate) {
            try {
                refreshPersonList()
            } catch (ex: Exception) {
                return Result.Error(ex)
            }
        }
        return storage.getPersonList()
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
        storage.addFavorites(person)
    }

    override suspend fun deleteFromFavorite(person: Person) {
        storage.deleteFromFavorites(person)
    }

    override suspend fun clearFavorites() {
        storage.clearFavorites()
    }

    override fun observePerson(url: String): LiveData<Result<Person>> {
        return storage.observePersonById(url)
    }
}
