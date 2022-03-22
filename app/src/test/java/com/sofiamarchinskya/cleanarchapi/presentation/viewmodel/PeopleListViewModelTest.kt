package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsRepository
import com.sofiamarchinskya.cleanarchapi.presentation.view.FilterType
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.mock


class PeopleListViewModelTest {
    private lateinit var repository: StarWarsRepository
    private lateinit var viewModel: PeopleListViewModel
    private lateinit var filteringLabelObserver: Observer<Int>
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

    @Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup()  {
        repository = mock()
        list.add(person1)
        list.add(person2)
        list.add(person3)
        filteringLabelObserver = mock()
        viewModel = PeopleListViewModel(repository)
        viewModel.currentFilteringLabel.observeForever(filteringLabelObserver)
    }

    @Test
    fun filterList() {
        viewModel.setFiltering(FilterType.FAVORITES)
        viewModel.currentFilteringLabel.observeForever{label->
            assertEquals(label, R.string.all_label)
        }
    }
    @Test
    fun ass(){
        assertEquals(1+1,2)
    }
}