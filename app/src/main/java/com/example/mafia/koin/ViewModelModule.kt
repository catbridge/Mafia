package com.example.mafia.koin

import com.example.mafia.viewModel.AddPlayersViewModel
import com.example.mafia.viewModel.HomeViewModel
import com.example.mafia.viewModel.NotesViewModel
import com.example.mafia.viewModel.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SharedViewModel()}
    viewModel { NotesViewModel(get())}
    viewModel { HomeViewModel(get())}
    viewModel { AddPlayersViewModel(get())}
}