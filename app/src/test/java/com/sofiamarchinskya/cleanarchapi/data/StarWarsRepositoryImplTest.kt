package com.sofiamarchinskya.cleanarchapi.data

import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsService
import com.sofiamarchinskya.cleanarchapi.data.storage.PersonStorage
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class StarWarsRepositoryImplTest {

    private val person1 = Person(
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

    private val person2 = Person(
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

    private val personrem1 = Person(
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

    private val personrem2 = Person(
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

    private val personrem3 = Person(
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

    private lateinit var remoteDataSource: StarWarsService
    private lateinit var localDataSource: PersonStorage

    // Class under test
    private lateinit var peopleRepository: StarWarsRepository

    @Before
    fun createRepository() {
        localDataSource = mock()
        remoteDataSource = mock()
        peopleRepository = StarWarsRepositoryImpl(
            remoteDataSource, localDataSource
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get list from local storage`() = runTest {
        Mockito.`when`(localDataSource.getPersonList()).thenReturn(Result.Success(localData))
        val result = peopleRepository.getPersonList()
        assertEquals(result, Result.Success(localData))
    }

    @Test
    fun `get exception from remote`() = runTest {
        Mockito.`when`(remoteDataSource.getPersonList())
            .thenReturn(Result.Error(Exception("Test exception")))
        val result = peopleRepository.getPersonList(true)
        assertEquals(result.toString(),Result.Error(Exception("Test exception")).toString())
    }
}
