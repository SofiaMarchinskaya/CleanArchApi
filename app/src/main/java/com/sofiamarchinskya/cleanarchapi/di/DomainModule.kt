package com.sofiamarchinskya.cleanarchapi.di

import com.sofiamarchinskya.cleanarchapi.domain.Interactor
import com.sofiamarchinskya.cleanarchapi.domain.InteractorImpl
import org.koin.dsl.module

 val domainModule = module {
     factory<Interactor> { InteractorImpl(get()) }
 }
