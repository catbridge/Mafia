package com.example.mafia.koin

import com.example.mafia.language.SharedPrefsManager
import org.koin.dsl.module

val sharedPrefsModule = module {
    single { SharedPrefsManager(get()) }
}