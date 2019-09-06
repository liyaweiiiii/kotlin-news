package com.example.kotlinnews.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinnews.R
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.main_fragment, container, false)
        rootView.news_list.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rootView.news_list.adapter = NewsAdapter()

        subscribeUI(viewModel)
        if (savedInstanceState == null){
            viewModel.loadKotlinNews()
        }

        return rootView
    }

    private fun subscribeUI(viewModel: MainViewModel) {
        viewModel.getNews().observe(this, Observer { news ->
            (news_list.adapter as NewsAdapter).setStoryList(news)
            (news_list.adapter as NewsAdapter).onItemClick = { newsItem ->
                val bundle = bundleOf("news" to newsItem.data)
                findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
            }
        })

        viewModel.getIsLoading().observe(this, Observer { value ->
            value?.let { show ->
                loading_spinner.visibility = if (show) View.VISIBLE else View.GONE
            }
        })

        viewModel.shouldShowError().observe(this, Observer { value ->
            value?.let { show ->
                tv_error.visibility = if (show) View.VISIBLE else View.GONE
            }
        })
    }

}
