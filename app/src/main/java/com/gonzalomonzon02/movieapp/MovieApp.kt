package com.gonzalomonzon02.movieapp

import android.app.Application
import com.gonzalomonzon02.movieapp.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApp)
            modules(appModules)
        }
    }
}
