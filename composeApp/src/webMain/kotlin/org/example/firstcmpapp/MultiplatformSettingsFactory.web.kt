package org.example.firstcmpapp

import com.russhwolf.settings.Settings
import com.russhwolf.settings.StorageSettings

actual class MultiplatformSettingsFactory {
    actual fun getSettings(): Settings {
        return StorageSettings()
    }
}