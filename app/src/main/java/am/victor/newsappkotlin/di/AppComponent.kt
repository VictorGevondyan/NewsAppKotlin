package am.victor.newsappkotlin.di

import am.victor.newsappkotlin.repositories.NewsRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by victor on 11/30/18.
 */

@Singleton
@Component(
    modules = arrayOf(AppModule::class)
        /*AndroidInjectionModule::class,*/
)
interface AppComponent {

//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        fun application(application: Application): Builder
//
//        fun build(): AppComponent
//    }

    fun inject(newsRepository: NewsRepository)
}