package com.example.tbc_homework16.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tbc_homework16.network.UserApi

class MainViewModelFactory(private val api: UserApi) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(api) as T
    }
}