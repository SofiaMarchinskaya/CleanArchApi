package com.sofiamarchinskya.cleanarchapi.data

import com.sofiamarchinskya.cleanarchapi.data.net.PersonServerModel
import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteEntity
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StarWarsRepositoryImplTest {

    private val person1 = FavoriteEntity(
        name = "name1",
        height = 111,
        url = "url1",
        mass = 222,
        hair_color = "hair_color1",
        skin_color = "skin_color1",
        eye_color = "eye_color1",
        birth_year = "birth_year1",
        gender = "gender1",
        homeworld = "homeworld1"
    )

    private val person2 = FavoriteEntity(
        name = "name2",
        height = 112,
        url = "url2",
        mass = 223,
        hair_color = "hair_color2",
        skin_color = "skin_color2",
        eye_color = "eye_color2",
        birth_year = "birth_year2",
        gender = "gender2",
        homeworld = "homeworld2"
    )

    private val personrem1 = PersonServerModel(
        name = "name1",
        height = 111,
        url = "url1",
        mass = 222,
        hair_color = "hair_color1",
        skin_color = "skin_color1",
        eye_color = "eye_color1",
        birth_year = "birth_year1",
        gender = "gender1",
        homeworld = "homeworld1"
    )

    private val personrem2 = PersonServerModel(
        name = "name2",
        height = 112,
        url = "url2",
        mass = 223,
        hair_color = "hair_color2",
        skin_color = "skin_color2",
        eye_color = "eye_color2",
        birth_year = "birth_year2",
        gender = "gender2",
        homeworld = "homeworld2"
    )

    private val personrem3 = PersonServerModel(
        name = "name3",
        height = 1132,
        url = "url3",
        mass = 2233,
        hair_color = "hair_color3",
        skin_color = "skin_color3",
        eye_color = "eye_color3",
        birth_year = "birth_year3",
        gender = "gender3",
        homeworld = "homeworld3"
    )

    private val remoteData = listOf(personrem1, personrem2, personrem3)
    private val localData = listOf(person1, person2)

    private lateinit var remoteDataSource: FakeService
    private lateinit var localDataSource: FakeStorage

    // Class under test
    private lateinit var peopleRepository: StarWarsRepository

    @Before
    fun createRepository() {
        remoteDataSource = FakeService(remoteData)
        localDataSource = FakeStorage(localData.toMutableList())
        // Get a reference to the class under test
        peopleRepository = StarWarsRepositoryImpl(
            remoteDataSource, localDataSource
        )
    }

    @Test
    fun getFavoritesList_requestsAllFromLocalDataSource() = runBlocking {

        var result = listOf<DomainPersonModel>()

        peopleRepository.getFavoritesList().collect { result = it }

        assertEquals(result, localData.map { it.toDomainPersonModel() })
    }

    @Test
    fun getPersonList_requestsAllFromLocalDataSource() = runTest {
        val result = peopleRepository.getPersonList()
        val expected = mapPerson(remoteData)
        assertEquals(result, expected)
    }

    private suspend fun mapPerson(personServerList: List<PersonServerModel>): List<DomainPersonModel> {
        return personServerList.map {
            DataPerson(it, localDataSource.isFavorite(it.url)).toDomainPersonModel()
        }
    }
}
