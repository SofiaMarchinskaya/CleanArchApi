package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.data.Result
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import com.sofiamarchinskya.cleanarchapi.getOrAwaitValue
import com.sofiamarchinskya.cleanarchapi.presentation.Event
import com.sofiamarchinskya.cleanarchapi.presentation.view.FilterType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class PeopleListViewModelTest {
    private lateinit var repository: StarWarsRepository
    private lateinit var viewModel: PeopleListViewModel
    private val dispatcher = UnconfinedTestDispatcher()
    private val scope = TestScope(dispatcher)
    private var list = HashMap<String, Person>()
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
        isfavorite = false
    )

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        list[person1.url] = person1
        list[person2.url] = person2
        list[person3.url] = person3
        repository = mock()
        viewModel = PeopleListViewModel(repository)
    }

    @Test
    fun `choose favorite filter`() {
        viewModel.setFiltering(FilterType.FAVORITES)
        viewModel.currentFilteringLabel.observeForever { label ->
            assertEquals(label, R.string.fav_label)
        }
    }

    @Test
    fun `choose all filter`() {
        viewModel.setFiltering(FilterType.ALL_PEOPLE)
        viewModel.currentFilteringLabel.observeForever { label ->
            assertEquals(label, R.string.all_label)
        }
    }

    @Test
    fun `choose not marked filter`() {
        viewModel.setFiltering(FilterType.NOT_FAVORITE)
        viewModel.currentFilteringLabel.observeForever { label ->
            assertEquals(label, R.string.not_fav_label)
        }
    }

    @Test
    fun `clear favorites`() = scope.runTest {
        Mockito.`when`(repository.observePersonList())
            .doReturn(flowOf(Result.Success(ArrayList(list.values))))
        Mockito.`when`(repository.clearFavorites())
            .thenAnswer { list.values.map { it.isfavorite = false } }
        viewModel.clearFavorites()
        val personlist = viewModel.items.getOrAwaitValue()
        assertEquals(personlist, ArrayList(list.values))
    }

    @Test
    fun `add person to favorites`() = scope.runTest {
        Mockito.`when`(repository.makeFavorite(person3))
            .doAnswer { list[person3.url]?.isfavorite = true }
        viewModel.addFavorites(person3, true)
        assertEquals(
            list[person3.url]?.isfavorite,
            true
        )

        val snackbarText = viewModel.snackbarText.getOrAwaitValue()
        assertEquals(snackbarText.getContentIfNotHandled(), R.string.add_to_favorites)
    }

    @Test
    fun `remove person from favorites`() = scope.runTest {
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
        Mockito.`when`(repository.makeFavorite(person3))
            .doAnswer { list[person3.url]?.isfavorite = false }

        assertEquals(
            list[person3.url]?.isfavorite,
            false
        )
        val snackbarText: Event<Int> = viewModel.snackbarText.getOrAwaitValue()
        assertEquals(snackbarText.getContentIfNotHandled(), R.string.remove_from_favorite)
    }
}