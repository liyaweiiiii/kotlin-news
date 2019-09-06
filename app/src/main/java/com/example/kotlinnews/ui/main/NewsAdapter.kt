package com.example.kotlinnews.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinnews.R
import com.example.kotlinnews.model.NewsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.viewholder_news_article.view.*

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {

    var onItemClick: ((NewsItem) -> Unit)? = null

    private var mNewsList = listOf<NewsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_news_article, parent, false))
    }

    override fun getItemCount() = mNewsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(mNewsList[position], onItemClick)
    }

    fun setStoryList(list: List<NewsItem>) {
        mNewsList = list
        notifyDataSetChanged()
    }

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun onBind(
        newsItem: NewsItem,
        onItemClick: ((NewsItem) -> Unit)?
    ) {
        itemView.apply {
            itemView.tv_title.text = newsItem.data.title
            if (newsItem.data.thumbnail.isNotBlank()){
                iv_thumbnail.visibility = View.VISIBLE
                Picasso.get().load(newsItem.data.thumbnail).into(iv_thumbnail)
            } else {
                iv_thumbnail.visibility = View.GONE
            }
            setOnClickListener {
                onItemClick?.invoke(newsItem)
            }
        }

    }

}