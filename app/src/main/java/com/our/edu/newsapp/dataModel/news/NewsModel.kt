package com.our.edu.newsapp.dataModel.news

data class NewsModel(
    val articles: MutableList<News>,
    val sortBy: String,
    val source: String,
    val status: String
)