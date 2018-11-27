package am.victor.newsapp.api

import am.victor.newsapp.models.NewsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by victor on 11/26/18.
 */
interface APITalker {

    /**
     * @GET declares an HTTP GET request
     * @Path("user") annotation on the userId parameter marks it as a
     * replacement for the {user} placeholder in the @GET path
     */
    @GET("/news/{new}")
    fun getNews(@Path("new") userId: String): Call<NewsItem>

}