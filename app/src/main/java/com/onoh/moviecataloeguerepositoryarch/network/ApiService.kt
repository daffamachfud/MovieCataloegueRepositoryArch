package com.onoh.moviecataloeguerepositoryarch.network


import com.onoh.moviecataloeguerepositoryarch.data.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("movie/popular")
    fun getAllMovie(@Query("api_key") apiKey: String): Call<MovieResponse>

    @GET("movie/{id}")
    fun getDetailMovie(@Path("id") id:Int ,@Query("api_key") apiKey: String): Call<DetailMvResponse>

    @GET("tv/popular")
    fun getAllTvShow(@Query("api_key") apiKey: String, ): Call<TvShowResponse>

    @GET("tv/{id}")
    fun getDetailTvShow(@Path("id")id:Int,@Query("api_key") apiKey: String):Call<DetailTvShowResponse>

    @GET("discover/movie")
    fun getDiscoverMovie(@Query("api_key") apiKey: String): Call<MovieResponse>

    @GET("discover/tv")
    fun getDiscoverTv(@Query("api_key") apiKey: String): Call<TvShowResponse>

    @GET("trending/all/day")
    fun getTrend(@Query("api_key") apiKey: String): Call<TrendResponse>

    @GET("movie/{id}/credits")
    fun getMovieCast(@Path("id")id:Int,@Query("api_key") apiKey: String):Call<CastResponse>

    @GET("tv/{id}/credits")
    fun getTvCast(@Path("id")id:Int,@Query("api_key") apiKey: String):Call<CastResponse>

}