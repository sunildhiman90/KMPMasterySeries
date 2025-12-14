package org.example.firstcmpapp.ch6_sharedPreferences

import com.russhwolf.settings.Settings

class AppPreferencesImpl(
    private val settings: Settings
): AppPreferences {

    override fun getInt(key: String, defaultValue: Int): Int {
        return settings.getInt(key, defaultValue)
    }

    override fun putInt(key: String, value: Int) {
        settings.putInt(key, value)
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return settings.getLong(key, defaultValue)
    }

    override fun putLong(key: String, value: Long) {
        settings.putLong(key, value)
    }

    override fun getString(key: String, defaultValue: String): String {
        return settings.getString(key, defaultValue)
    }

    override fun putString(key: String, value: String) {
        settings.putString(key, value)
    }

    override fun hasKey(key: String): Boolean {
        return settings.hasKey(key)
    }

    override fun remove(key: String) {
        settings.remove(key)
    }

    override fun clear() {
        settings.clear()
    }


}