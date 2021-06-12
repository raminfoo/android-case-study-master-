package com.target.targetcasestudy.dagger

import android.app.Application
import com.target.targetcasestudy.dagger.component.AppComponent
import com.target.targetcasestudy.dagger.component.DaggerAppComponent
import com.target.targetcasestudy.dagger.module.NetworkModule

class App : Application(){

    companion object{ lateinit var appComponent: AppComponent }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .networkModule(NetworkModule(this))
            .build()
    }
}