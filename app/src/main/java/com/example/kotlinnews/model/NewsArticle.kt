package com.example.kotlinnews.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsArticle(
    val title: String,
    val thumbnail: String,
    val selftext: String
): Parcelable