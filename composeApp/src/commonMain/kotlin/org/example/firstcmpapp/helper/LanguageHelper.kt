package org.example.firstcmpapp.helper

interface LanguageHelper {

    fun setLanguageCode(languageCode: String)
    suspend fun changeLanguageCode(languageCode: String)
    suspend fun getLanguageNameByCode(languageCode: String): String?
    suspend fun getCurrentLanguage(): String

}

