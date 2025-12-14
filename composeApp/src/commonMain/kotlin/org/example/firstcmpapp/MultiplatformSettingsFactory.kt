package org.example.firstcmpapp

import com.russhwolf.settings.Settings

expect class MultiplatformSettingsFactory {
    fun getSettings(): Settings
}