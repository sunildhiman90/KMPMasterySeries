package org.example.firstcmpapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import org.example.firstcmpapp.ch8_localDatabaseSqldelight.DatabaseDriverFactory
import org.example.firstcmpapp.di.initKoin


val koin = initKoin()

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        App()
    }
}