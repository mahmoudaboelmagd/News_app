package com.our.edu.newsapp.dataModel

import java.io.Serializable

class DomainNewsModel(
    var author: String?,
    var description: String?,
    var publishedAt: String?,
    var title: String?,
    var url: String?,
    var urlToImage: String?
) : Serializable