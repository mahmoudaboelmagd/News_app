package com.our.edu.newsapp

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.our.edu.newsapp.dataModel.news.News
import com.our.edu.newsapp.ui.fragments.home.NewsAdapter
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class NewsListAdapterTest {

  private val context = ApplicationProvider.getApplicationContext<Context>()

  // validate that adapter size
  @Test fun getItemCount() {
    val adapter = NewsAdapter(context)
    assertEquals(0, adapter.itemCount)

    adapter.setWords(arrayListOf(News("Link Development",
      "Link Development test","30-06-2021","Link","https://www.linkdevelopment.com","https://www.linkdevelopment.com")))
    assertEquals(1, adapter.itemCount)
  }
}