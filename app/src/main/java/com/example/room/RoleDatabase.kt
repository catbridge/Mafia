package com.example.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mafia.model.Role

@Database(entities = [Role::class], version = 1)
abstract class RoleDatabase : RoomDatabase() {

    abstract fun roleDao(): RoleDao
}