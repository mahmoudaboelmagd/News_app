package com.our.edu.newsapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.our.edu.newsapp.R
import com.our.edu.newsapp.dataModel.news.News
import com.our.edu.newsapp.databinding.FragmentHomeBinding
import com.our.edu.newsapp.utils.Constants
import com.our.edu.newsapp.utils.Utilities
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding

    private lateinit var homeViewModel: HomeViewModel

    private var newsList: MutableList<News> = ArrayList()

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (this::homeBinding.isInitialized) {
            homeBinding
        } else {
            homeBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

            loadData()

            homeBinding.swipeToRefresh.setOnRefreshListener {
                newsList.clear()
                newsAdapter.notifyDataSetChanged()
                homeViewModel.getNews()
            }

        }

        newsList.clear()

        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        homeViewModel.associatedArticles.observe(viewLifecycleOwner) { articles ->
            newsList.addAll(articles)

            setData(newsList)

        }

        homeViewModel.webArticles.observe(viewLifecycleOwner) { articles ->
            newsList.addAll(articles)

            setData(newsList)
        }

        homeViewModel.requestFail.observe(viewLifecycleOwner) {

            if (newsAdapter.itemCount == 0) {
                homeViewModel.noData.set(true)
            }

        }

    }


    private fun initAdapter(){
        newsAdapter = NewsAdapter(requireContext()).apply {
            registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onChanged() {
                    super.onChanged()
                    if (this@apply.itemCount == 0) {
                        homeBinding.noData.visibility = View.VISIBLE
                    }
                }
            })
        }

        homeBinding.recyclerArticles.adapter = newsAdapter
        homeBinding.recyclerArticles.layoutManager = LinearLayoutManager(activity)
    }

    private fun loadData() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeBinding.hvm = homeViewModel
        homeBinding.lifecycleOwner = this
        homeViewModel.getNews()
    }

    private fun setData(list: MutableList<News>) {
        if (list.size > 0){
            homeViewModel.noData.set(false)
            newsAdapter.setWords(list)
            Utilities.runAnimation(
                homeBinding.recyclerArticles,
                Constants.ANIMATION_RIGHT_TO_LEFT
            )
        }else {
            homeViewModel.noData.set(true)
        }
    }
}