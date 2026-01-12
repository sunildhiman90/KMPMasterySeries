package org.example.firstcmpapp.di

import org.example.firstcmpapp.DbHelper
import org.example.firstcmpapp.ProductsDao
import org.example.firstcmpapp.ProductsDaoImpl
import org.koin.dsl.module

fun cacheModule() = module {
    single {
        DbHelper(get())
    }

    single<ProductsDao> {
        ProductsDaoImpl(get())
    }
}