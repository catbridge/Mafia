package com.example.mafia.koin

import androidx.room.Room
import com.example.room.RoleDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            RoleDatabase::class.java,
            "mafia.db"
        )
            .build()
    }

    single {
        get<RoleDatabase>().roleDao()
    }
}