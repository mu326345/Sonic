package com.yuyu.sonic.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yuyu.sonic.home.HomeViewModel
import com.yuyu.sonic.repository.DefaultRepository
import com.yuyu.sonic.repository.IRepository

class ViewModelFactory(val repository: IRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown class")
    }
}