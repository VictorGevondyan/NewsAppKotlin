package am.victor.newsappkotlin.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query


/**
 * Created by victor on 11/30/18.
 */
@Dao
interface NewsDao {
    @Insert(onConflict = REPLACE)
    fun save(newsItem: RoomNewsItem)

    @Query("SELECT * FROM RoomNewsItem WHERE id = :newsId")
    fun load(newsId: Int): LiveData<RoomNewsItem>
}