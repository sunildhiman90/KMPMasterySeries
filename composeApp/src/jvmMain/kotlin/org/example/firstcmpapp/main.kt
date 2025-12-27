package org.example.firstcmpapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "FirstCMPApp",
    ) {
        App(
            databaseDriverFactory = DatabaseDriverFactory(),
            org.example.firstcmpapp.ch6_sharedPreferences.MultiplatformSettingsFactory())
    }
}