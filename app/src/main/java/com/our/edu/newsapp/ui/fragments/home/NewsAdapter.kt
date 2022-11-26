package com.our.edu.newsapp.ui.fragments.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.our.edu.newsapp.R
import com.our.edu.newsapp.dataModel.news.News
import com.our.edu.newsapp.databinding.ItemNewsBinding
import com.our.edu.newsapp.mapper.NewsNetworkMapper
import com.our.edu.newsapp.utils.Constants
import com.squareup.picasso.Picasso


class NewsAdapter(private val context: Context) :
    RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    private var articles: MutableList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_news,
            parent,
            false
        )
        return ArticleViewHolder(binding as ItemNewsBinding)
    }

    class ArticleViewHolder internal constructor(var binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        holder.binding.titleTxt.text = articles[position].title
        holder.binding.authorTxt.text =
            context.getString(R.string.by) + " " + articles[position].author
        holder.binding.publishedAtTxt.text = articles[position].publishedAt

        Picasso.get().load(articles[position].urlToImage)
            .placeholder(R.drawable.placeholder).into(holder.binding.articleImg)

        holder.itemView.setOnClickListener {
            val bundle = Bundle()

            // map server model into domain model
            val mapper = NewsNetworkMapper()
            val domainApplicationModel = mapper.mapFromServer(articles[position])
            bundle.putSerializable(Constants.ARGUMENT_KEY, domainApplicationModel)
            Navigation.createNavigateOnClickListener(R.id.nav_details, bundle)
                .onClick(holder.itemView)
        }
    }


    //set adapter list when new data are observed
    fun setWords(articles: MutableList<News>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    override fun getItemCount() = articles.size
}