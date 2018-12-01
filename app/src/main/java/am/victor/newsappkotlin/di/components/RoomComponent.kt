package am.victor.newsappkotlin.di.components

import am.victor.newsappkotlin.NewsApplication
import am.victor.newsappkotlin.di.modules.RoomModule
import am.victor.newsappkotlin.repositories.NewsRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by victor on 11/30/18.
 */

@Singleton
@Component(
    modules =
    arrayOf(
        RoomModule::class
    )
)
interface RoomComponent {
//    fun inject(newsRepository: NewsRepository)
}