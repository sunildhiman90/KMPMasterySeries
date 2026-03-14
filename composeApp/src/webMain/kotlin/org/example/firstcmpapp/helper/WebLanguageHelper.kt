package org.example.firstcmpapp.helper


import org.example.firstcmpapp.ch6_sharedPreferences.AppPreferences
import org.example.firstcmpapp.domain.usecase.GetLanguagesUseCase
import kotlin.js.ExperimentalWasmJsInterop


@OptIn(ExperimentalWasmJsInterop::class)
@JsFun("(locale) => { window.__customLocale = locale; }")
private external fun setCustomLocale(locale: String)

class WebLanguageHelper(
    private val appPreferences: AppPreferences,
    private val getLanguagesUseCase: GetLanguagesUseCase,
) : CommonLanguageHelper(
    appPreferences, getLanguagesUseCase
) {

    override suspend fun changeLanguageCode(languageCode: String) {
        super.changeLanguageCode(languageCode)
        setCustomLocale(languageCode.replace('_','-'))

    }
}