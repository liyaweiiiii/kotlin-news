package com.example.kotlinnews.network

import com.example.kotlinnews.model.NewsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient : INetworkClient {
    private val networkApi = Retrofit.Builder()
        .baseUrl("https://www.reddit.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NetworkApi::class.java)

    override suspend fun getNews(): NewsResponse {
        return networkApi.getNews()
    }
}