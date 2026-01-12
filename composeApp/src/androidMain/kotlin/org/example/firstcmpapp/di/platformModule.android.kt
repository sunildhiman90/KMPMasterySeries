package org.example.firstcmpapp.di

import org.example.firstcmpapp.ch6_sharedPreferences.MultiplatformSettingsFactory
import org.example.firstcmpapp.ch8_localDatabaseSqldelight.DatabaseDriverFactory
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        MultiplatformSettingsFactory(get())
    }

    single {
        DatabaseDriverFactory(get())
    }
}