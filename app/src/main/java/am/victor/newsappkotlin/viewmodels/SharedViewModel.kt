package am.victor.newsapp.viewmodels

import am.victor.newsapp.fragments.dummy.DummyContent
import am.victor.newsapp.models.NewsItem
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by victor on 11/26/18.
 */
class SharedViewModel: ViewModel() {

    private lateinit var newsList: MutableLiveData<List<DummyContent.DummyItem>>

//    val selected = MutableLiveData<DummyContent.DummyItem>()

    fun getUsers(): LiveData<List<DummyContent.DummyItem>> {
        if (!::newsList.isInitialized) {
            newsList = MutableLiveData()
            loadNews()
        }
        return newsList
    }

    private fun loadNews() {
        // Do an asynchronous operation to fetch users.
    }

//    fun select(item: DummyContent.DummyItem) {
//        selected.value = item
//    }

}