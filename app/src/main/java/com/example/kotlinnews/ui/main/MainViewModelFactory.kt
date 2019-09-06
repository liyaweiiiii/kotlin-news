package com.example.kotlinnews.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinnews.network.INetworkClient

class MainViewModelFactory(private val network: INetworkClient): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java!!)) {
            MainViewModel(this.network) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}