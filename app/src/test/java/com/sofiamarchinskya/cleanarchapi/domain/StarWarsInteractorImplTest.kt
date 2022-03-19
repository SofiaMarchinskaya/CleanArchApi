package com.sofiamarchinskya.cleanarchapi.domain

import com.sofiamarchinskya.cleanarchapi.data.FakeRepository
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class StarWarsInteractorImplTest {
    private val interactor = StarWarsInteractorImpl(FakeRepository())
    private val expected = listOf(
        DomainPersonModel(
            name = "name",
            height = 1,
            url = "url",
            mass = 12,
            hair_color = "hair_color",
            skin_color = "skin_color",
            eye_color = "eye_color",
            birth_year = "birth_year",
            gender = "gender",
            homeworld = "homeworld",
            isFavourite = true
        ),
        DomainPersonModel(
            name = "na",
            height = 1,
            url = "ul",
            mass = 12,
            hair_color = "hair_color",
            skin_color = "skinolor",
            eye_color = "eye_color",
            birth_year = "birth_yar",
            gender = "geder",
            homeworld = "homeorld",
            isFavourite = true
        )
    )

    @Test
    fun `should return the same list as in repository`(): Unit =
        runBlocking {
            Assert.assertEquals(interactor.getItemList(), expected)
        }

    @Test
    fun `should return the same list of favorites as in repository`(): Unit = runBlocking {
        interactor.getFavoritesList().map {
            it.forEachIndexed { index, domainPersonModel ->
                Assert.assertEquals(
                    domainPersonModel,
                    expected[index]
                )
            }
        }
    }


    @Test
    fun insertFavorites() {

    }

    @Test
    fun removeFromFavorites() {
    }
}