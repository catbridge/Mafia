package com.example.mafia.language

import android.content.Context

class SharedPrefsManager(context: Context) {

    private val sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var language: Language by enumPref(KEY_LANGUAGE_MODE, Language.RU)

    private inline fun <reified E : Enum<E>> enumPref(key: String, defaultValue: E) =
        PrefsDelegate(
            sharedPrefs,
            getValue = { getString(key, null)?.let(::enumValueOf) ?: defaultValue },
            setValue = { putString(key, it.name) }
        )


    companion object {
        private const val PREFS_NAME = "prefs"
        private const val KEY_LANGUAGE_MODE = "lang"
    }
}