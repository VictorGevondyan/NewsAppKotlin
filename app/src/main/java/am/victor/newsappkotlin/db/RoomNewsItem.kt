package am.victor.newsappkotlin.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey



/**
 * Created by victor on 11/30/18.
 */
@Entity
class RoomNewsItem{

    @PrimaryKey
    var id: String = ""
    var title: String = ""
    var urlToImage: String = ""
    var publishedAt: String = ""
    var content: String = ""

}