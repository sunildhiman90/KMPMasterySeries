package org.example.firstcmpapp.domain.usecase

import firstcmpapp.composeapp.generated.resources.Res
import firstcmpapp.composeapp.generated.resources.france
import firstcmpapp.composeapp.generated.resources.ic_hindi
import firstcmpapp.composeapp.generated.resources.uk
import org.example.firstcmpapp.AppConstants.EN
import org.example.firstcmpapp.AppConstants.FR
import org.example.firstcmpapp.AppConstants.HI
import org.example.firstcmpapp.model.AppLanguageItem

interface GetLanguagesUseCase {
    operator fun invoke(): List<AppLanguageItem>
}

class GetLanguagesUseCaseImpl : GetLanguagesUseCase {
    override fun invoke(): List<AppLanguageItem> =
        listOf(

            //These languages also we can use from strings, if we want to add localizaton support for them
            AppLanguageItem(
                name = "English",
                code = EN,
                flagDrawable = Res.drawable.uk,
            ),
            AppLanguageItem(
                name = "French",
                code = FR,
                flagDrawable = Res.drawable.france,
            ),
            AppLanguageItem(
                name = "Hindi",
                code = HI,
                flagDrawable = Res.drawable.ic_hindi,
            ),
        )
}