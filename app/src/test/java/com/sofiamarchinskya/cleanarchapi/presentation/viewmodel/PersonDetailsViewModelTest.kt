package com.sofiamarchinskya.cleanarchapi.presentation.viewmodel

import com.sofiamarchinskya.cleanarchapi.data.FakeRepository
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsInteractor
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsInteractorImpl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PersonDetailsViewModelTest {
    private lateinit var repository: FakeRepository
    private lateinit var personDetailsViewModel: PersonDetailsViewModel
    private lateinit var interactor: StarWarsInteractor

    @Before
    fun setupViewModel() {
        repository = FakeRepository()
        interactor = StarWarsInteractorImpl(repository)
        personDetailsViewModel = PersonDetailsViewModel(interactor)
    }

    @Test
    fun `insert favorites`() = runTest {
        personDetailsViewModel.addToFavourites()
    }
}