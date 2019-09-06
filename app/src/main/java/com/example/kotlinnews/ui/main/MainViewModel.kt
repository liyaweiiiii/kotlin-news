package com.example.kotlinnews.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinnews.model.NewsItem
import com.example.kotlinnews.network.INetworkClient
import kotlinx.coroutines.launch

class MainViewModel(private val network: INetworkClient) : ViewModel() {

    private lateinit var news: MutableLiveData<List<NewsItem>>

    fun getNews(): LiveData<List<NewsItem>> {
        if (!::news.isInitialized) {
            news = MutableLiveData()
        }
        return news
    }

    private lateinit var isLoading: MutableLiveData<Boolean>

    fun getIsLoading(): LiveData<Boolean> {
        if (!::isLoading.isInitialized) {
            isLoading = MutableLiveData()
        }
        return isLoading
    }

    private lateinit var showError: MutableLiveData<Boolean>

    fun shouldShowError(): LiveData<Boolean> {
        if (!::showError.isInitialized) {
            showError = MutableLiveData()
        }
        return showError
    }

    fun loadKotlinNews() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                news.value = network.getNews().data.children
            } catch (e: Exception) {
                showError.value = true
                e.printStackTrace()
            } finally {
                isLoading.value = false
            }

        }
    }

}
