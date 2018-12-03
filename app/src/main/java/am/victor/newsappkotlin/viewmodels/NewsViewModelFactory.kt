package am.victor.newsappkotlin.viewmodels

import am.victor.newsapp.viewmodels.NewsViewModel
import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.util.Log

/**
 * Created by victor on 12/2/18.
 */
class NewsViewModelFactory (val context: Context,
    private val areOnlineNews: Boolean
): ViewModelProvider.NewInstanceFactory() {
    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        return NewsViewModel(context, areOnlineNews) as T
    }
}