package am.victor.newsappkotlin.models

import am.victor.newsapp.models.NewsItem
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.gson.annotations.SerializedName

/**
 * Created by victor on 11/28/18.
 */

class NewsResponseWrapper {

    var status:String = ""

    var totalResults:Int = 0

    @SerializedName("articles")
    var newsList:List<NewsItem> = listOf()
}
