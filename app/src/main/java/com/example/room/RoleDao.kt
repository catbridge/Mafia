package com.example.room

import androidx.room.*
import com.example.mafia.model.Role

@Dao
interface RoleDao{
    @Query("SELECT * FROM role")
     suspend fun getRoles(): List<Role>

    @Query("SELECT * FROM role")
     suspend fun getRole(): Role

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoles(roles: List<Role>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRole(role: Role)

    @Delete
    suspend fun deleteRole(role: Role)

    @Delete
    suspend fun deleteRoles(roles: List<Role>)
}