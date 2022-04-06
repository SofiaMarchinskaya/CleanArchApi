package com.sofiamarchinskya.cleanarchapi.di

import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PeopleListViewModel
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PersonDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PeopleListViewModel(get()) }
    viewModel { PersonDetailsViewModel(get()) }
}
