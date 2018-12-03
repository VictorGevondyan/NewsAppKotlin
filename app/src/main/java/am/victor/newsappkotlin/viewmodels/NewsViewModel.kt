package am.victor.newsapp.viewmodels

import am.victor.newsappkotlin.di.AppModule
import am.victor.newsappkotlin.di.DaggerAppComponent
import am.victor.newsappkotlin.repositories.NewsRepository
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import javax.inject.Inject


/**
 * Created by victor on 11/26/18.
 */
class NewsViewModel(context: Context, val areOnlineNews:Boolean): ViewModel() {

    var newsObject: LiveData<Any>

    @Inject
    lateinit var newsRepository: NewsRepository

    init {

        DaggerAppComponent.builder()
            .appModule(AppModule(context))
            .build()
            .inject(this);

            newsObject = newsRepository.getNews(areOnlineNews)

    }

}