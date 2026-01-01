package org.example.firstcmpapp

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    App(
        databaseDriverFactory = org.example.firstcmpapp.ch8_localDatabaseSqldelight.DatabaseDriverFactory(),
        org.example.firstcmpapp.ch6_sharedPreferences.MultiplatformSettingsFactory()
    )
}