package org.example.firstcmpapp

import android.app.Application
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.firstcmpapp.ch6_sharedPreferences.AppPreferences
import org.example.firstcmpapp.di.initKoin
import org.example.firstcmpapp.helper.LanguageHelper
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext

class MainApplication: Application() {

    val scope = MainScope()

    private val appPreferences: AppPreferences by inject()
    private val languageHelper: LanguageHelper by inject()

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(applicationContext)
        }
        setupLanguages()

    }

    fun setupLanguages() {
        scope.launch {
            val currentLanguage = appPreferences.getString(AppConstants.LANGUAGE, AppConstants.EN)
            withContext(Dispatchers.IO) {
                languageHelper.changeLanguageCode(currentLanguage)
            }
        }
    }

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        setupLanguages()
    }



}