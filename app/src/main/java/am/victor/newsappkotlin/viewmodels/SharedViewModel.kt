package am.victor.newsapp.viewmodels

import am.victor.newsapp.models.NewsItem
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by victor on 11/26/18.
 */
class SharedViewModel: ViewModel() {

    private lateinit var newsList: MutableLiveData<List<NewsItem>>

    val selected = MutableLiveData<NewsItem>()

    fun getUsers(): LiveData<List<NewsItem>> {
        if (!::newsList.isInitialized) {
            newsList = MutableLiveData()
            loadNews()
        }
        return newsList
    }

    private fun loadNews() {
        // Do an asynchronous operation to fetch users.
    }

    fun select(item: NewsItem) {
        selected.value = item
    }

}