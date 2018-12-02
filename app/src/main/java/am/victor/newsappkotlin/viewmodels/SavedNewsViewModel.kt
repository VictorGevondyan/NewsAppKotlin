package am.victor.newsappkotlin.viewmodels

import am.victor.newsapp.models.NewsItem
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by victor on 12/2/18.
 */
class SavedNewsViewModel: ViewModel() {

    private lateinit var savedNewsList: MutableLiveData<List<NewsItem>>

//    val selected = MutableLiveData<DummyContent.DummyItem>()

    fun getSavedNews(): LiveData<List<NewsItem>> {
        if (!::savedNewsList.isInitialized) {
            savedNewsList = MutableLiveData()
            loadSavedNews()
        }
        return savedNewsList
    }

    private fun loadSavedNews() {
        // Do an asynchronous operation to get news from db.
    }

/*    fun select(item: DummyContent.DummyItem) {
        selected.value = item
    }*/

}