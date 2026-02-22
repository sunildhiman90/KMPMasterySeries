package org.example.firstcmpapp.di

import com.russhwolf.settings.Settings
import org.example.firstcmpapp.ch6_sharedPreferences.AppPreferences
import org.example.firstcmpapp.ch6_sharedPreferences.AppPreferencesImpl
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val commonModule = module {
    single<AppPreferences> { AppPreferencesImpl(get()) }
}

fun initKoin(
    appDeclaration: KoinAppDeclaration? = null
) = startKoin {
    appDeclaration?.invoke(this)
    modules(platformModule() + cacheModule() + viewModelModule() + commonModule)
}