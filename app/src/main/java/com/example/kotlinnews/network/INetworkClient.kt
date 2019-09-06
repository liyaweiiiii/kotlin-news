package com.example.kotlinnews.network

import com.example.kotlinnews.model.NewsResponse

interface INetworkClient {
    suspend fun getNews(): NewsResponse
}