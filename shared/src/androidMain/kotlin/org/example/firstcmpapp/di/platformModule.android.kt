package org.example.firstcmpapp.di

import org.example.firstcmpapp.ch6_sharedPreferences.MultiplatformSettingsFactory
import org.example.firstcmpapp.ch8_localDatabaseSqldelight.DatabaseDriverFactory
import org.example.firstcmpapp.helper.AndroidLanguageHelper
import org.example.firstcmpapp.helper.LanguageHelper
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        MultiplatformSettingsFactory(get())
    }

    single {
        DatabaseDriverFactory(get())
    }

    single<LanguageHelper> {
        AndroidLanguageHelper(get(), get(), get())
    }
}