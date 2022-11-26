package com.our.edu.newsapp.repository

import androidx.annotation.WorkerThread
import com.our.edu.newsapp.apis.ServicesInterface
import com.our.edu.newsapp.dataModel.news.NewsModel
import retrofit2.Call
import javax.inject.Inject

class ServicesRepository @Inject constructor(private val servicesInterface: ServicesInterface) {


    @WorkerThread
    fun getTheNextWebNews() : Call<NewsModel> {
        return servicesInterface.getTheNextWebNews()
    }

    @WorkerThread
    fun getAssociatedPressNews() : Call<NewsModel> {
        return servicesInterface.getAssociatedPressNews()
    }

}