package com.sofiamarchinskya.cleanarchapi.domain

import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InteractorImpl(private val repository: StarWarsRepository) : Interactor {

    override fun observePersonList(): Flow<Result<List<Person>>> {
        return repository.observePersonList()
    }

    override suspend fun getPersonList(update: Boolean): Result<List<Person>> {
        return repository.getPersonList()
    }

    override suspend fun refreshPersonList() {
        repository.refreshPersonList()
    }

    override suspend fun addFavorite(person: Person, isFavorite: Boolean): Int {
        return if (isFavorite) {
            repository.makeFavorite(person)
            R.string.add_to_favorites
        } else {
            repository.deleteFromFavorite(person)
            R.string.remove_from_favorite
        }
    }

    override suspend fun clearFavorites() {
        repository.clearFavorites()
    }

    override fun observePerson(url: String): Flow<Result<Person>> {
        return repository.observePerson(url)
    }
}