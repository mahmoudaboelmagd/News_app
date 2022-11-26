package com.our.edu.newsapp

import com.our.edu.newsapp.di.AppModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class NewsApiTest {

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


    @Test
    fun theNextWebApiTestSuccess() = runBlocking {

        val expected = 200

        val response = AppModule.createRetrofitClient().getAssociatedPressNews().execute()

        val receivedTime: Long = response.raw().receivedResponseAtMillis()
        val sentTime: Long = response.raw().sentRequestAtMillis()
        println("the next web response time : " + (receivedTime - sentTime) + " ms")

        val resultCode = response.code()

        Assert.assertEquals(expected, resultCode)
    }


    @Test
    fun associatedPressApiTest() = runBlocking {
        val expected = 200

        val response = AppModule.createRetrofitClient().getTheNextWebNews().execute()

        val receivedTime: Long = response.raw().receivedResponseAtMillis()
        val sentTime: Long = response.raw().sentRequestAtMillis()
        println("associatedPress response time : " + (receivedTime - sentTime) + " ms")

        val resultCode = response.code()

        Assert.assertEquals(expected, resultCode)
    }

}