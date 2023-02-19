package com.example.mafia.language

import java.util.*

enum class Language(val locale: Locale) {
    EN(locale = Locale.ENGLISH),
    RU(locale = Locale("ru"))
}