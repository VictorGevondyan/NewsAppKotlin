package am.victor.newsappkotlin.di.components

import am.victor.newsappkotlin.NewsApplication
import am.victor.newsappkotlin.di.modules.AppModule
import am.victor.newsappkotlin.repositories.NewsRepository
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
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