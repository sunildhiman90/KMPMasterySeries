package org.example.firstcmpapp.model

import org.jetbrains.compose.resources.DrawableResource


data class AppLanguageItem(
    val name: String,
    val code: String,
    val flagDrawable: DrawableResource
)