package org.example.firstcmpapp.helper

import org.example.firstcmpapp.AppConstants.EN
import org.example.firstcmpapp.AppConstants.LANGUAGE
import org.example.firstcmpapp.ch6_sharedPreferences.AppPreferences
import org.example.firstcmpapp.domain.usecase.GetLanguagesUseCase

abstract class CommonLanguageHelper(
    private val appPreferences: AppPreferences,
    private val getLanguagesUseCase: GetLanguagesUseCase,
): LanguageHelper {

    override fun setLanguageCode(languageCode: String) {
        appPreferences.putString(LANGUAGE, languageCode)
    }

    override suspend fun changeLanguageCode(languageCode: String) {
        setLanguageCode(languageCode)
    }

    override suspend fun getLanguageNameByCode(languageCode: String): String? {
        return getLanguagesUseCase.invoke().firstOrNull { it.code == languageCode }?.name
    }

    override suspend fun getCurrentLanguage(): String {
        return getLanguageNameByCode(appPreferences.getString(LANGUAGE, EN)) ?: EN
    }


}