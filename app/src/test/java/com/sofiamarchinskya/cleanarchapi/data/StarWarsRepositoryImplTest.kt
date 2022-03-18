package com.sofiamarchinskya.cleanarchapi.data

import com.sofiamarchinskya.cleanarchapi.data.storage.database.FavoriteEntity
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
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

    // private val remoteTasks = listOf(task1, task2).sortedBy { it.id }
    private val localData = listOf(person1, person2)

    private lateinit var remoteDataSource: FakeService
    private lateinit var localDataSource: FakeStorage

    // Class under test
    private lateinit var tasksRepository: StarWarsRepository

    @Before
    fun createRepository() {
        remoteDataSource = FakeService()
        localDataSource = FakeStorage(localData.toMutableList())
        // Get a reference to the class under test
        tasksRepository = StarWarsRepositoryImpl(
            remoteDataSource, localDataSource
        )
    }

    @Test
    fun getTasks_requestsAllTasksFromRemoteDataSource() = runBlocking {
        // When tasks are requested from the tasks repository
        var result = listOf<DomainPersonModel>()

        tasksRepository.getFavoritesList().collect { result = it }

        assertEquals(result, localData.map { it.toDomainPersonModel() })
    }

}
