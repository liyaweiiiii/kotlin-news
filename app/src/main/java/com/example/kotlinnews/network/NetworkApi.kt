package com.example.kotlinnews.network

import com.example.kotlinnews.model.NewsResponse
import retrofit2.http.GET

interface NetworkApi {
    @GET("/r/kotlin/.json")
    suspend fun getNews(): NewsResponse
}