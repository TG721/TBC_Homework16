package com.example.tbc_homework16.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.example.tbc_homework16.network.UserApi

class MainViewModel(
    private val api: UserApi
) : ViewModel() {
    val users =
        Pager(config = PagingConfig(pageSize = 2), pagingSourceFactory = {
            com.example.tbc_homework16.paging.PagingSource(api)
        }).flow.cachedIn(viewModelScope)
}