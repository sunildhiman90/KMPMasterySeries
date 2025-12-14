package org.example.firstcmpapp.ch6_sharedPreferences

import com.russhwolf.settings.Settings

expect class MultiplatformSettingsFactory {
    fun getSettings(): Settings
}