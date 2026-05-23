package org.example.firstcmpapp.helper


import org.example.firstcmpapp.ch6_sharedPreferences.AppPreferences
import org.example.firstcmpapp.domain.usecase.GetLanguagesUseCase
import java.util.Locale

class JvmLanguageHelper(
    private val appPreferences: AppPreferences,
    private val getLanguagesUseCase: GetLanguagesUseCase,
) : CommonLanguageHelper(
    appPreferences, getLanguagesUseCase
) {

    override suspend fun changeLanguageCode(languageCode: String) {
        super.changeLanguageCode(languageCode)

        Locale.setDefault(Locale.forLanguageTag(languageCode))

    }
}