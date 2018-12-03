package am.victor.newsappkotlin.db

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database


/**
 * Created by victor on 11/30/18.
 */
@Database(entities = arrayOf(RoomNewsItem::class), version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}