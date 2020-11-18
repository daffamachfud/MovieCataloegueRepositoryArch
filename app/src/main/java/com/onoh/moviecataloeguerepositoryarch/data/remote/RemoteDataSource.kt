package com.onoh.moviecataloeguerepositoryarch.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onoh.moviecataloeguerepositoryarch.BuildConfig
import com.onoh.moviecataloeguerepositoryarch.data.remote.response.*
import com.onoh.moviecataloeguerepositoryarch.network.RetrofitClient
import com.onoh.moviecataloeguerepositoryarch.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource{
    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null
        const val apiKey = BuildConfig.API_KEY
        fun getInstance(): RemoteDataSource = instance ?: synchronized(this){
            instance ?: RemoteDataSource()
        }
    }

    private val apiService = RetrofitClient.api()

    fun getAllMovies(): LiveData<ApiResponse<List<MoviesResult?>?>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MoviesResult?>?>>()
        apiService.getAllMovie(apiKey).enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if(response.isSuccessful){
                   resultMovies.value = ApiResponse.success(response.body()?.results)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("onResponse",t.message.toString())
            }
        })
        return resultMovies
    }

    fun getDetailMovie(movieId:Int) : LiveData<ApiResponse<DetailMvResponse?>>{
        EspressoIdlingResource.increment()
        val detailMovie = MutableLiveData<ApiResponse<DetailMvResponse?>>()
        apiService.getDetailMovie(movieId, apiKey).enqueue(object : Callback<DetailMvResponse>{
            override fun onResponse(call: Call<DetailMvResponse>, response: Response<DetailMvResponse>) {
                if(response.isSuccessful){
                   detailMovie.value = ApiResponse.success(response.body())
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<DetailMvResponse>, t: Throwable) {
                Log.d("onResponse",t.message.toString())
            }
        })
        return detailMovie
    }

    fun getAllTvShow():LiveData<ApiResponse<List<TvShowsResult?>?>>{
        EspressoIdlingResource.increment()
        val resultAllTvShow = MutableLiveData<ApiResponse<List<TvShowsResult?>?>>()
        apiService.getAllTvShow(apiKey).enqueue(object : Callback<TvShowResponse>{
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if(response.isSuccessful){
                    resultAllTvShow.value = ApiResponse.success(response.body()?.results)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.d("onResponse",t.message.toString())
            }
        })
        return resultAllTvShow
    }

    fun getDetailTvShow(tvshowId:Int) : LiveData<ApiResponse<DetailTvShowResponse?>>{
        EspressoIdlingResource.increment()
        val detailTvshow = MutableLiveData<ApiResponse<DetailTvShowResponse?>>()
        apiService.getDetailTvShow(tvshowId, apiKey).enqueue(object :Callback<DetailTvShowResponse>{
            override fun onResponse(call: Call<DetailTvShowResponse>, response: Response<DetailTvShowResponse>) {
                if(response.isSuccessful){
                   detailTvshow.value = ApiResponse.success(response.body())
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                Log.d("onResponse",t.message.toString())
            }
        })
        return detailTvshow
    }

    fun getDiscoveryMovie():LiveData<ApiResponse<List<MoviesResult?>?>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MoviesResult?>?>>()
        apiService.getDiscoverMovie(apiKey).enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if(response.isSuccessful){
                    resultMovies.value = ApiResponse.success(response.body()?.results)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("onResponse",t.message.toString())
            }
        })
        return resultMovies
    }

    fun getDiscoveryTv():LiveData<ApiResponse<List<TvShowsResult?>?>> {
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<List<TvShowsResult?>?>>()
        apiService.getDiscoverTv(apiKey).enqueue(object : Callback<TvShowResponse>{
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if(response.isSuccessful){
                    resultTv.value = ApiResponse.success(response.body()?.results)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.d("onResponse",t.message.toString())
            }
        })
        return resultTv
    }

    fun getTrend():LiveData<ApiResponse<List<TrendResult?>?>> {
        EspressoIdlingResource.increment()
        val resultTrend = MutableLiveData<ApiResponse<List<TrendResult?>?>>()
        apiService.getTrend(apiKey).enqueue(object : Callback<TrendResponse>{
            override fun onResponse(call: Call<TrendResponse>, response: Response<TrendResponse>) {
                if(response.isSuccessful){
                    resultTrend.value = ApiResponse.success(response.body()?.results)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<TrendResponse>, t: Throwable) {
                Log.d("onResponse",t.message.toString())
            }
        })
        return resultTrend
    }

    fun getMovieCast(movieId: Int,callback: LoadCastCallback){
        EspressoIdlingResource.increment()
        apiService.getMovieCast(movieId,apiKey).enqueue(object : Callback<CastResponse>{
            override fun onResponse(call: Call<CastResponse>, response: Response<CastResponse>) {
                if(response.isSuccessful){
                   callback.onAllCastReceived(response.body()?.cast)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<CastResponse>, t: Throwable) {
                Log.d("onResponse",t.message.toString())
            }
        })
    }

    fun getTvCast(tvId: Int,callback: LoadCastCallback) {
        EspressoIdlingResource.increment()
        apiService.getTvCast(tvId,apiKey).enqueue(object : Callback<CastResponse>{
            override fun onResponse(call: Call<CastResponse>, response: Response<CastResponse>) {
                if(response.isSuccessful){
                    callback.onAllCastReceived(response.body()?.cast)
                    EspressoIdlingResource.decrement()
                }
            }
            override fun onFailure(call: Call<CastResponse>, t: Throwable) {
                Log.d("onResponse",t.message.toString())
            }
        })
    }

    //tanpa offline mode
    interface LoadCastCallback{
        fun onAllCastReceived(castResult:List<CastResult?>?)
    }

}