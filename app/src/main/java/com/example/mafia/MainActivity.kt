package com.example.mafia

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mafia.extensions.applySelectedAppLanguage
import com.example.mafia.language.SharedPrefsManager
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val prefsManager: SharedPrefsManager by inject()

    override fun attachBaseContext(newBase: Context) {
            super.attachBaseContext(newBase.applySelectedAppLanguage(prefsManager.language))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}