package am.victor.newsappkotlin.di

import am.victor.newsapp.api.NewsService
import am.victor.newsappkotlin.db.NewsDatabase
import android.arch.persistence.room.Room
import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by victor on 11/30/18.
 */

@Module
class AppModule {

    var context: Context

    constructor(context: Context){
        this.context = context
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideNewsDatabase( context: Context): NewsDatabase {
        return Room.databaseBuilder(context, NewsDatabase::class.java, "NewsDatabase")
            .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    fun provideNewsService(): NewsService {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val newsService = retrofit.create(NewsService::class.java)
        return newsService

    }

}