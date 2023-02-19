package com.example.mafia.extensions

import android.content.Context
import android.content.res.Configuration
import com.example.mafia.language.Language
import java.util.*

fun Context.applySelectedAppLanguage(language: Language): Context {
        val newConfig = Configuration(resources.configuration)
        Locale.setDefault(language.locale)
        newConfig.setLocale(language.locale)

        return createConfigurationContext(newConfig)
    }
