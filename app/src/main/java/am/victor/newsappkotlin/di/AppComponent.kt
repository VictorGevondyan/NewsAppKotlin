package am.victor.newsappkotlin.di

import am.victor.newsapp.viewmodels.NewsViewModel
import am.victor.newsappkotlin.repositories.NewsRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by victor on 11/30/18.
 */

@Singleton
@Component(
    modules = arrayOf(AppModule::class)
)
interface AppComponent {

    fun inject(newsRepository: NewsRepository)

    fun inject(newsViewModel: NewsViewModel)
}