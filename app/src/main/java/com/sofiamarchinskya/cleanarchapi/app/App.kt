package com.sofiamarchinskya.cleanarchapi.app

import android.app.Application
import com.sofiamarchinskya.cleanarchapi.di.AppComponent
import com.sofiamarchinskya.cleanarchapi.di.AppModule
import com.sofiamarchinskya.cleanarchapi.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}