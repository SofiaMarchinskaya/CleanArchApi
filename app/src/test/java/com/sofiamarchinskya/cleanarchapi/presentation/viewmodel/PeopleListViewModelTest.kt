package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sofiamarchinskya.cleanarchapi.MainCoroutineRule
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.data.FakeRepository
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.getOrAwaitValue
import com.sofiamarchinskya.cleanarchapi.presentation.view.FilterType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PeopleListViewModelTest {
    private var repository = FakeRepository()
    private lateinit var viewModel: PeopleListViewModel
    private var list = mutableListOf<Person>()
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

    private val person3 = Person(
        name = "name3",
        height = 1132,
        url = "url3",
        mass = 2233,
        hair_color = "hair_color3",
        skin_color = "skin_color3",
        eye_color = "eye_color3",
        birth_year = "birth_year3",
        gender = "gender3",
        homeworld = "homeworld3",
        isfavorite = true
    )

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        list.add(person1)
        list.add(person2)
        list.add(person3)
        repository.setPeopleData(list)
        viewModel = PeopleListViewModel(repository)
    }

    @Test
    fun `choose favorite filter`() {
        viewModel.setFiltering(FilterType.FAVORITES)
        assertEquals(viewModel.currentFilteringLabel.value, R.string.fav_label)
    }

    @Test
    fun `choose all filter`() {
        viewModel.setFiltering(FilterType.ALL_PEOPLE)
        assertEquals(viewModel.currentFilteringLabel.value, R.string.all_label)

    }

    @Test
    fun `choose not marked filter`() {
        viewModel.setFiltering(FilterType.NOT_FAVORITE)
        assertEquals(viewModel.currentFilteringLabel.value, R.string.not_fav_label)
    }

    @Test
    fun `clear favorites, items with property isfavorite = true removed`() {
        viewModel.clearFavorites()
        viewModel.items.observeForever { personList ->
            assertEquals(personList, list.filter { !it.isfavorite })
        }
    }

    @Test
    fun `add person to favorites`() {
        val person3 = Person(
            name = "name3",
            height = 1132,
            url = "url3",
            mass = 2233,
            hair_color = "hair_color3",
            skin_color = "skin_color3",
            eye_color = "eye_color3",
            birth_year = "birth_year3",
            gender = "gender3",
            homeworld = "homeworld3",
            isfavorite = true
        )

        viewModel.addFavorites(person3, true)
        assertEquals(
            repository.peopleData[person3.url]?.isfavorite, true
        )
        assertEquals(viewModel.snackbarText.value, R.string.add_to_favorites)
    }

    @Test
    fun `remove person from favorites`() {
        val person3 = Person(
            name = "name3",
            height = 1132,
            url = "url3",
            mass = 2233,
            hair_color = "hair_color3",
            skin_color = "skin_color3",
            eye_color = "eye_color3",
            birth_year = "birth_year3",
            gender = "gender3",
            homeworld = "homeworld3",
            isfavorite = true
        )

        viewModel.addFavorites(person3, false)

        assertEquals(
            repository.peopleData[person3.url]?.isfavorite == false,
            true
        )
        assertEquals(viewModel.snackbarText.value, R.string.remove_from_favorite)
    }

    @Test
    fun `error while downloading list`() {
        repository.setReturnError(true)
        viewModel.items.observeForever {}
        assertEquals(viewModel.snackbarText.value, R.string.loading_error)
    }
}