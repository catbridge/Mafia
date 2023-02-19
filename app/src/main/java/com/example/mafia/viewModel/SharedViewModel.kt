package com.example.mafia.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mafia.model.Role
import com.example.mafia.utils.Constants
import com.example.room.RoleDao
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class SharedViewModel: ViewModel() {
    val numberPlayers = MutableLiveData<Int>()

    fun select(number: Int){
        numberPlayers.value = number
    }
}