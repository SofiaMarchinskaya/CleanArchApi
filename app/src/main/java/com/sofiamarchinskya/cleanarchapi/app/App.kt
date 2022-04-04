package com.sofiamarchinskya.cleanarchapi.app

import android.app.Application
import com.sofiamarchinskya.cleanarchapi.di.AppComponent
import com.sofiamarchinskya.cleanarchapi.di.AppModule
import com.sofiamarchinskya.cleanarchapi.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}