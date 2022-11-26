package com.our.edu.newsapp.ui.fragments.home

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.our.edu.newsapp.dataModel.news.News
import com.our.edu.newsapp.dataModel.news.NewsModel
import com.our.edu.newsapp.repository.ServicesRepository
import com.our.edu.newsapp.utils.ErrorHandler
import com.our.edu.newsapp.utils.Utilities
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel @ViewModelInject constructor(
    application: Application,
    private val servicesRepository: ServicesRepository
) : AndroidViewModel(application),
    LifecycleObserver {

    private val presenterJob = Job()
    private val coroutineScope = CoroutineScope(presenterJob + Dispatchers.IO)

    val associatedArticles:MutableLiveData<MutableList<News>> = MutableLiveData()
    val webArticles:MutableLiveData<MutableList<News>> = MutableLiveData()

    private val context = getApplication<Application>().applicationContext

    val noData:ObservableBoolean = ObservableBoolean(true)

    val isLoading = ObservableBoolean(false)

    val requestFail:MutableLiveData<Boolean> = MutableLiveData()

    // request 2 apis in parallel with coroutines
    fun getNews() {
        coroutineScope.launch {
            isLoading.set(true)
            val resultOneDeferred = async { getTheNextWebData() }
            val resultTwoDeferred = async { getAssociatedData() }
            resultOneDeferred.await()
            resultTwoDeferred.await()
        }
    }

    private fun getTheNextWebData() {

        servicesRepository
            .getTheNextWebNews().enqueue(object :
                Callback<NewsModel> {
                override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                    isLoading.set(false)
                    Utilities.toastyError(context, t.message.toString())
                }

                override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                    requestFail.value = true
                    isLoading.set(false)
                    if (ErrorHandler.handleError(
                            context,
                            response.code(),
                            response.errorBody()
                        )
                    ) {
                        webArticles.value = response.body()!!.articles
                    }
                }
            })
    }

    private fun getAssociatedData() {

        servicesRepository
            .getAssociatedPressNews().enqueue(object :
                Callback<NewsModel> {
                override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                    requestFail.value = true
                    isLoading.set(false)
                    Utilities.toastyError(context, t.message.toString())
                }

                override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                    isLoading.set(false)
                    if (ErrorHandler.handleError(
                            context,
                            response.code(),
                            response.errorBody()
                        )
                    ) {
                        associatedArticles.value = response.body()!!.articles
                    }
                }
            })
    }

}