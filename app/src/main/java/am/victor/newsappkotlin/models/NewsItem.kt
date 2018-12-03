package am.victor.newsapp.models

import am.victor.newsappkotlin.models.NewsItemSource
import com.google.gson.annotations.SerializedName

/**
 * Created by victor on 11/26/18.
 */
class NewsItem {

    @SerializedName("source")
    var newsSource: NewsItemSource? = null

    var title: String = ""

    var urlToImage: String = ""

    var publishedAt: String = ""

    var content: String = ""

}