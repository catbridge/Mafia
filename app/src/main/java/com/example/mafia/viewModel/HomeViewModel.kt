package com.example.mafia.viewModel

import androidx.lifecycle.ViewModel
import com.example.room.RoleDao

class HomeViewModel(private val roleDao: RoleDao): ViewModel() {

    suspend fun clearDB(){
        val roles = roleDao.getRoles()
        if(roles.isNotEmpty()) roleDao.deleteRoles(roles)
    }
}