package am.victor.newsappkotlin.repositories

import am.victor.newsapp.api.NewsService
import am.victor.newsapp.models.NewsItem
import am.victor.newsappkotlin.db.NewsDao
import am.victor.newsappkotlin.db.RoomNewsItem
import am.victor.newsappkotlin.di.AppModule
import am.victor.newsappkotlin.di.DaggerAppComponent
import am.victor.newsappkotlin.models.NewsResponseWrapper
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import android.content.Context
import android.support.annotation.WorkerThread
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


/**
 * Created by victor on 11/29/18.
 */
class NewsRepository(context: Context) {

    @Inject
    lateinit var newsService: NewsService

    @Inject
    lateinit var newsDao: NewsDao

    init {

        DaggerAppComponent.builder()
            .appModule(AppModule(context))
            .build()
            .inject(this);

    }

    fun getNews(areOnlineNews: Boolean): LiveData<Any> {

        // This isn't an optimal implementation. We'll fix it later.
        return if (areOnlineNews) {
            getOnlineNews()
        } else {
            getSavedNews()
        }

    }

    private fun getOnlineNews(): LiveData<Any> {

        val data = MutableLiveData<Any>()

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

    @WorkerThread
    private fun getSavedNews(): LiveData<Any> {

        val newsItemList = mutableListOf<NewsItem>()
        val roomNewsItemList = newsDao.loadAll().value
        if (roomNewsItemList != null) {
            for (roomNewsItem in roomNewsItemList) {
                newsItemList.add(parseRoomNewsItem(roomNewsItem))
            }
        }

        return newsItemList as LiveData<Any>

    }

    @WorkerThread
    fun saveNewsItem(newsItem: NewsItem) {
        newsDao.save(parseNewsItem(newsItem))
    }

    @WorkerThread
    fun deleteNewsItem(newsItem: NewsItem) {
        newsDao.delete(parseNewsItem(newsItem))
    }

    private fun parseRoomNewsItem(roomNewsItem: RoomNewsItem): NewsItem {
        val newsItem = NewsItem()
        newsItem.newsSource!!.id = roomNewsItem.id
        newsItem.title = roomNewsItem.title
        newsItem.urlToImage = roomNewsItem.urlToImage
        newsItem.publishedAt = roomNewsItem.publishedAt
        newsItem.content = roomNewsItem.content

        return newsItem
    }

    private fun parseNewsItem(newsItem: NewsItem): RoomNewsItem {
        val roomNewsItem = RoomNewsItem()
        roomNewsItem.id = newsItem.newsSource!!.id
        roomNewsItem.title = newsItem.title
        roomNewsItem.urlToImage = newsItem.urlToImage
        roomNewsItem.publishedAt = newsItem.publishedAt
        roomNewsItem.content = newsItem.content

        return roomNewsItem
    }

}