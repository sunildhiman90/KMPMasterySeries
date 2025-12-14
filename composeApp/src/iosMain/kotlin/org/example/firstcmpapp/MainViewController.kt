package org.example.firstcmpapp

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { App(org.example.firstcmpapp.ch6_sharedPreferences.MultiplatformSettingsFactory()) }