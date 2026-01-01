package org.example.firstcmpapp

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import org.example.firstcmpapp.ch8_localDatabaseSqldelight.DatabaseDriverFactory

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        App(
            databaseDriverFactory = DatabaseDriverFactory(),
            org.example.firstcmpapp.ch6_sharedPreferences.MultiplatformSettingsFactory()
        )
    }
}