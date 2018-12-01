package am.victor.newsappkotlin.repositories

import am.victor.newsapp.api.NewsService
import am.victor.newsappkotlin.NewsApplication
import am.victor.newsappkotlin.db.NewsDao
import am.victor.newsappkotlin.db.NewsDatabase
import am.victor.newsappkotlin.di.components.DaggerAppComponent
import am.victor.newsappkotlin.di.components.DaggerNewsServiceComponent
import am.victor.newsappkotlin.di.components.DaggerRoomComponent
import am.victor.newsappkotlin.di.modules.AppModule
import am.victor.newsappkotlin.models.NewsResponseWrapper
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import android.content.Context
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by victor on 11/29/18.
 */
class NewsRepository (context: Context)/*@Inject constructor(newsService: NewsService, newsDatabase: NewsDatabase)*/ {

    @Inject
    lateinit var newsService: NewsService

    @Inject
    lateinit var newsDatabase: NewsDatabase

    private val executor: Executor? = null

    init {
//        DaggerNewsServiceComponent.create().inject(this)
//        DaggerRoomComponent.create().inject(this)
        DaggerAppComponent.builder()
            .appModule(AppModule(context))
            .build()
            .inject(this);
    }

    fun getNews(): LiveData<NewsResponseWrapper> {
        // This isn't an optimal implementation. We'll fix it later.
        val data = MutableLiveData<NewsResponseWrapper>()

        val newsCall = newsService.getNews()
        newsCall.enqueue(object : Callback<NewsResponseWrapper> {

            override fun onFailure(call: Call<NewsResponseWrapper>?, t: Throwable?) {
                Log.d("News call Failure", t.toString())
            }

            override fun onResponse(
                call: Call<NewsResponseWrapper>?,
                response: Response<NewsResponseWrapper>?
            ) {
                data.setValue(response?.body())
            }

        })

        return data
    }

}