package com.our.edu.newsapp.mapper

import com.our.edu.newsapp.dataModel.news.News
import com.our.edu.newsapp.dataModel.DomainNewsModel

class NewsNetworkMapper : MapperClass<News, DomainNewsModel> {

    override fun mapFromServer(server: News): DomainNewsModel {
        return DomainNewsModel(
            author = server.author,
            description = server.description,
            publishedAt = server.publishedAt,
            title = server.title,
            url = server.url,
            urlToImage = server.urlToImage,
        )
    }

}