package com.example.kotlinnews.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsItem (
    val data: NewsArticle
): Parcelable
