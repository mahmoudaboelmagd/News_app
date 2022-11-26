package com.our.edu.newsapp.dataModel.news

data class News(
    val author: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)