package com.example.kotlinnews.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinnews.model.NewsArticle

class DetailViewModel : ViewModel() {
    private lateinit var title: MutableLiveData<String>

    fun getTitle(): LiveData<String> {
        if (!::title.isInitialized) {
            title = MutableLiveData()
        }
        return title
    }

    private lateinit var imageUrl: MutableLiveData<String>

    fun getImageUrl(): LiveData<String> {
        if (!::imageUrl.isInitialized) {
            imageUrl = MutableLiveData()
        }
        return imageUrl
    }

    private lateinit var content: MutableLiveData<String>

    fun getContent(): LiveData<String> {
        if (!::content.isInitialized) {
            content = MutableLiveData()
        }
        return content
    }

    fun loadNewsDetail(newsArticle: NewsArticle) {
        if (newsArticle.thumbnail.isNotBlank()) {
            imageUrl.value = newsArticle.thumbnail
        }

        title.value = newsArticle.title
        content.value = newsArticle.selftext
    }
}