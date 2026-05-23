package org.example.firstcmpapp.di

import org.example.firstcmpapp.ch6_sharedPreferences.MultiplatformSettingsFactory
import org.example.firstcmpapp.ch8_localDatabaseSqldelight.DatabaseDriverFactory
import org.example.firstcmpapp.helper.JvmLanguageHelper
import org.example.firstcmpapp.helper.LanguageHelper
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        MultiplatformSettingsFactory()
    }

    single {
        DatabaseDriverFactory()
    }

    single<LanguageHelper> {
        JvmLanguageHelper(get(), get())
    }
}