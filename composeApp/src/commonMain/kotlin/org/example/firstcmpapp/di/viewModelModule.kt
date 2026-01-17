package org.example.firstcmpapp.di

import org.example.firstcmpapp.AppViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule()  = module {
    viewModel<AppViewModel> {
        AppViewModel(
            productsDao = get()
        )
    }
}