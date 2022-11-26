package com.our.edu.newsapp.apis

import com.our.edu.newsapp.dataModel.news.NewsModel
import com.our.edu.newsapp.utils.Constants
import retrofit2.Call
import retrofit2.http.GET

interface ServicesInterface {

    @GET("articles?source=the-next-web&apiKey=${Constants.API_KEY}")
    fun getTheNextWebNews(): Call<NewsModel>

    @GET("articles?source=associated-press&apiKey=${Constants.API_KEY}")
    fun getAssociatedPressNews(): Call<NewsModel>

}