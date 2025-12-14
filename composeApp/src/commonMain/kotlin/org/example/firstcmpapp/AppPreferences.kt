package org.example.firstcmpapp

interface AppPreferences {

    fun getInt(
        key: String,
        defaultValue: Int
    ): Int

    fun putInt(
        key: String,
        value: Int
    )

    fun getLong(
        key: String,
        defaultValue: Long
    ): Long

    fun putLong(
        key: String,
        value: Long
    )

    fun getString(
        key: String,
        defaultValue: String
    ): String

    fun putString(
        key: String,
        value: String
    )

    fun hasKey(
        key: String
    ): Boolean

    fun remove(
        key: String
    )

    fun clear()

}