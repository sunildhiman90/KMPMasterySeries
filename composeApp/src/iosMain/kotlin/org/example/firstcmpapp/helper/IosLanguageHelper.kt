package org.example.firstcmpapp.helper


import org.example.firstcmpapp.ch6_sharedPreferences.AppPreferences
import org.example.firstcmpapp.domain.usecase.GetLanguagesUseCase
import platform.Foundation.NSUserDefaults

class IosLanguageHelper(
    private val appPreferences: AppPreferences,
    private val getLanguagesUseCase: GetLanguagesUseCase,
) : CommonLanguageHelper(
    appPreferences, getLanguagesUseCase
) {

    override suspend fun changeLanguageCode(languageCode: String) {
        super.changeLanguageCode(languageCode)

        val userDefaults = NSUserDefaults.standardUserDefaults
        userDefaults.setObject(languageCode, "AppleLanguages")
        userDefaults.synchronize()

    }
}