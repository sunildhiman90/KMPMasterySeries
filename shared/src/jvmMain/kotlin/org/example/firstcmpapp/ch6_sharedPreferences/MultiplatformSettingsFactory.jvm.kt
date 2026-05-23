package org.example.firstcmpapp.ch6_sharedPreferences

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

actual class MultiplatformSettingsFactory {
    actual fun getSettings(): Settings {
        val pref = Preferences.userRoot()
        return PreferencesSettings(pref)
    }
}