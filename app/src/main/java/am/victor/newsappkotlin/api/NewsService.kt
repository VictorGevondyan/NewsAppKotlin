package am.victor.newsapp.api

import am.victor.newsapp.models.NewsItem
import am.victor.newsappkotlin.models.NewsResponseWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject


/**
 * Created by victor on 11/26/18.
 */
interface NewsService{

    /**
     * @GET declares an HTTP GET request
     *
     */
    @GET("v2/top-headlines?country=us&apiKey=aa21eee618cd4f2cad012e0762542ff8")
    fun getNews(): Call<NewsResponseWrapper>

}