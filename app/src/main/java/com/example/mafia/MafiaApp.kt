package com.example.mafia

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.mafia.koin.databaseModule
import com.example.mafia.koin.sharedPrefsModule
import com.example.mafia.koin.viewModelModule
import com.example.room.RoleDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MafiaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MafiaApp)
            modules(
                databaseModule,
                viewModelModule,
                sharedPrefsModule
            )
        }
    }
}