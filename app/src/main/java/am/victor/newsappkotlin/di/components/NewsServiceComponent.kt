package am.victor.newsappkotlin.di.components

import am.victor.newsappkotlin.di.modules.NewsServiceModule
import am.victor.newsappkotlin.di.modules.RoomModule
import am.victor.newsappkotlin.repositories.NewsRepository
import dagger.Component
import javax.inject.Singleton


/**
 * Created by victor on 11/29/18.
 */
@Singleton
@Component(
    modules =
    arrayOf(
        NewsServiceModule::class
    )
)

interface NewsServiceComponent {

//    fun inject(newsRepository: NewsRepository)
}