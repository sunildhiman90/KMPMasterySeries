package org.example.firstcmpapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.firstcmpapp.ch8_localDatabaseSqldelight.DatabaseDriverFactory
import org.example.firstcmpapp.di.initKoin

val koin = initKoin()

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "FirstCMPApp",
    ) {
        App()
    }
}