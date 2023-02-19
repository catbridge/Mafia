package com.example.mafia.viewModel

import androidx.lifecycle.ViewModel
import com.example.mafia.model.Role
import com.example.mafia.utils.Constants
import com.example.room.RoleDao

class AddPlayersViewModel(private val roleDao: RoleDao): ViewModel() {

    suspend fun addPlayer(name: String, role: String) {
        roleDao.insertRole(
            Role(name = name, role = role)
        )
    }


    fun createRoleList(number: Int): List<String> {
        return when (number) {
            6 -> Constants.SIX_PLAYERS
            7 -> Constants.SEVEN_PLAYERS
            8 -> Constants.EIGHT_PLAYERS
            9 -> Constants.NINE_PLAYERS
            10 -> Constants.TEN_PLAYERS
            11 -> Constants.ELEVEN_PLAYERS
            12 -> Constants.TWELVE_PLAYERS
            13 -> Constants.THIRTEEN_PLAYERS
            else -> Constants.ERROR_MESSAGE
        }
    }

}