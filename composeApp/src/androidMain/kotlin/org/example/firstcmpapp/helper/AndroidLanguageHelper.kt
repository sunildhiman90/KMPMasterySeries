package org.example.firstcmpapp.helper

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import org.example.firstcmpapp.ch6_sharedPreferences.AppPreferences
import org.example.firstcmpapp.domain.usecase.GetLanguagesUseCase
import java.util.Locale

class AndroidLanguageHelper(
    private val appPreferences: AppPreferences,
    private val getLanguagesUseCase: GetLanguagesUseCase,
    private val context: Context
) : CommonLanguageHelper(
    appPreferences, getLanguagesUseCase
) {

    override suspend fun changeLanguageCode(languageCode: String) {
        super.changeLanguageCode(languageCode)

        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val resources = context.resources
        val configuration = resources.configuration
        configuration.setLocales(LocaleList.forLanguageTags(languageCode))
        context.createConfigurationContext(configuration)

        //Per app language support
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags(languageCode)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
        }

    }
}