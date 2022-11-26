package com.our.edu.newsapp.dataModel.error

data class Error(
    val detail: String,
    val status: Int,
    val title: String
)