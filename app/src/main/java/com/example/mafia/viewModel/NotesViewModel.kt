package com.example.mafia.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mafia.model.Role
import com.example.room.RoleDao
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

class NotesViewModel(private val roleDao: RoleDao): ViewModel() {

    private val loadMoreFlow = MutableSharedFlow<Unit>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    val dataFlow = flow{
        emit(roleDao.getRoles())
    }.shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)

    suspend fun getRoles(): List<Role>{
       return roleDao.getRoles()
    }


    suspend fun deleteRole(role: Role){
        roleDao.deleteRole(role)
    }
}