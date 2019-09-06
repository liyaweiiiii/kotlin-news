package com.example.kotlinnews.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.kotlinnews.R
import com.example.kotlinnews.model.NewsArticle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_detail, container, false)

        subscribeUI(viewModel)
        if (savedInstanceState == null) {
            arguments?.getParcelable<NewsArticle>("news")?.let {
                viewModel.loadNewsDetail(it)
            }
        }

        return rootView
    }

    private fun subscribeUI(viewModel: DetailViewModel) {
        viewModel.getTitle().observe(this, Observer {
            (activity as AppCompatActivity).supportActionBar!!.title = it
        })

        viewModel.getImageUrl().observe(this, Observer {
            iv_thumbnail.visibility = View.VISIBLE
            Picasso.get().load(it).into(iv_thumbnail)
        })

        viewModel.getContent().observe(this, Observer {
            detail_text.text = it
        })
    }


}
