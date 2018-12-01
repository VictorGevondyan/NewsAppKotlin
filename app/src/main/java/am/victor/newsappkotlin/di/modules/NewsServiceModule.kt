package am.victor.newsappkotlin.di.modules

import am.victor.newsapp.api.NewsService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by victor on 11/29/18.
 */
@Module
class NewsServiceModule {

//    @Provides
//    @Singleton
//    fun provideNewsService(): NewsService {
//
//        val gson = GsonBuilder()
//            .setLenient()
//            .create()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://newsapi.org/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//
//        val newsService = retrofit.create(NewsService::class.java)
//        return newsService
//
//    }

}