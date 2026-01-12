package org.example.firstcmpapp

import android.app.Application
import org.example.firstcmpapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(applicationContext)
        }
    }


}