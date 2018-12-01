package am.victor.newsappkotlin.di.modules

import am.victor.newsappkotlin.NewsApplication
import am.victor.newsappkotlin.db.NewsDatabase
import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by victor on 11/30/18.
 */

@Module
class RoomModule {

//    private var application: NewsApplication
//
//    constructor(application: NewsApplication){
//        this.application = application
//    }
//
//    @Provides
//    @Singleton
//    fun provideContext(): Context {
//        return application
//    }
//
//    @Provides
//    @Singleton
//    fun provideNewsDatabase( context: Context): NewsDatabase {
//        return Room.databaseBuilder(context, NewsDatabase::class.java, "NewsDatabase")
//            .fallbackToDestructiveMigration()
//            .build()
//    }

}