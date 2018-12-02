package am.victor.newsappkotlin.repositories

import am.victor.newsapp.api.NewsService
import am.victor.newsapp.models.NewsItem
import am.victor.newsappkotlin.db.NewsDatabase
import am.victor.newsappkotlin.db.RoomNewsItem
import am.victor.newsappkotlin.di.AppModule
import am.victor.newsappkotlin.di.DaggerAppComponent
import am.victor.newsappkotlin.models.NewsResponseWrapper
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask
import android.support.annotation.WorkerThread
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor
import javax.inject.Inject


/**
 * Created by victor on 11/29/18.
 */
class NewsRepository(context: Context) {

    @Inject
    lateinit var newsService: NewsService

    @Inject
    lateinit var newsDatabase: NewsDatabase

    private val executor: Executor? = null

    private val context = context

    init {

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
                AsyncTask.execute({ saveRoomNewsItem() })
            }

        })

        return data
    }

    @WorkerThread
    fun getSavedNews(): LiveData<List<RoomNewsItem>> {
        return newsDatabase.newsDao().loadAll()
    }

    @WorkerThread
    fun saveNewsItem( newsItem: NewsItem ) {
        newsDatabase.newsDao().save(roomNewsItem)
        val roomNewsItem2 = newsDatabase.newsDao().load(1)
        Log.d("ROOM NEWS ITEM:", roomNewsItem2?.title)
    }

}