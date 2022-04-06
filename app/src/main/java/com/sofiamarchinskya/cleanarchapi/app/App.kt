package com.sofiamarchinskya.cleanarchapi.app

import android.app.Application
import com.sofiamarchinskya.cleanarchapi.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(viewModelModule, dataModule, networkModule, domainModule))
        }
    }
}