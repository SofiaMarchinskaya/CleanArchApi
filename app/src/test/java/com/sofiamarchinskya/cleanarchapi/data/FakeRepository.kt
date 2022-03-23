package com.sofiamarchinskya.cleanarchapi.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import kotlinx.coroutines.runBlocking

class FakeRepository : StarWarsRepository {

    var peopleData: LinkedHashMap<String, Person> = LinkedHashMap()
    private val observablePeopleList = MutableLiveData<Result<List<Person>>>()
    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    fun setPeopleData(list: List<Person>) {
        for (item in list) {
            peopleData[item.url] = item
        }
        runBlocking { refreshPersonList() }
    }

    override fun observePersonList(): LiveData<Result<List<Person>>> {
        return observablePeopleList
    }

    override suspend fun getPersonList(forceUpdate: Boolean): Result<List<Person>> {
        if (shouldReturnError) {
            return Result.Error(Exception("Test exception"))
        }
        return Result.Success(peopleData.values.toList())
    }

    override suspend fun refreshPersonList() {
        observablePeopleList.value = getPersonList()
    }

    override suspend fun getPerson(url: String, forceUpdate: Boolean): Result<Person> {
        if (shouldReturnError) {
            return Result.Error(Exception("Test exception"))
        }
        peopleData[url]?.let {
            return Result.Success(it)
        }
        return Result.Error(Exception("Could not find person"))
    }

    override suspend fun makeFavorite(person: Person) {
        val favPerson = person.copy(isfavorite = true)
        peopleData[person.url] = favPerson
        refreshPersonList()
    }

    override suspend fun deleteFromFavorite(person: Person) {
        val delPerson = person.copy(isfavorite = false)
        peopleData[person.url] = delPerson
        refreshPersonList()
    }

    override suspend fun clearFavorites() {
        peopleData = peopleData.filterValues {
            !it.isfavorite
        } as LinkedHashMap<String, Person>

    }

    override fun observePerson(url: String): LiveData<Result<Person>> {
        return observablePeopleList.map { result ->
            when (result) {
                is Result.Loading -> Result.Loading
                is Result.Error -> Result.Error(result.exception)
                is Result.Success -> {
                    val task = result.data.firstOrNull { it.url == url }
                        ?: return@map Result.Error(Exception("Not found"))
                    Result.Success(task)
                }
            }
        }
    }
}