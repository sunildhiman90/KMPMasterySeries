package org.example.firstcmpapp

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual class MultiplatformSettingsFactory {
    actual fun getSettings(): Settings {
        val prefs = NSUserDefaults.standardUserDefaults
        return NSUserDefaultsSettings(prefs)
    }
}