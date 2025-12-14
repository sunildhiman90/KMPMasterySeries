package org.example.firstcmpapp

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

private const val ANDROID_SETTINGS_NAME = "android_settings"

actual class MultiplatformSettingsFactory(private val context: Context) {
    actual fun getSettings(): Settings {
        val pref = context.getSharedPreferences(ANDROID_SETTINGS_NAME, Context.MODE_PRIVATE)
        return SharedPreferencesSettings(pref)
    }
}