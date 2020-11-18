package com.onoh.moviecataloeguerepositoryarch.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.*
import com.onoh.moviecataloeguerepositoryarch.data.local.room.AppDao

class LocalDataSource private constructor(private val mAppDao: AppDao){

    companion object{
        private var INSTANCE : LocalDataSource? = null

        fun getInstance(appDao: AppDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(appDao)
    }

    fun getAllMovies(): DataSource.Factory<Int,MovieEntity> = mAppDao.getAllMovies()

    fun getAllTvShows(): DataSource.Factory<Int,TvShowEntity> = mAppDao.getAllTvShows()

    fun getDsvMovies(): DataSource.Factory<Int,DiscoveryMovieEntity> = mAppDao.getDsvMovies()

    fun getDsvTv(): DataSource.Factory<Int,DiscoveryTvEntity> = mAppDao.getDsvTv()

    fun getTrend(): DataSource.Factory<Int,TrendEntity> = mAppDao.getTrend()

    fun getFavoriteMovies() : DataSource.Factory<Int,MovieEntity> = mAppDao.getFavoriteMovies()

    fun getFavoriteTvshows() :  DataSource.Factory<Int,TvShowEntity> = mAppDao.getFavoriteTvshows()

    fun getLocalDetailMovie(movieId : Int) : LiveData<MovieEntity> = mAppDao.getDetailMovie(movieId)

    fun getLocalDetailTvshow(tvShowId : Int) : LiveData<TvShowEntity> = mAppDao.getDetailTvShow(tvShowId)

    fun insertAllMovies(movies: List<MovieEntity>) = mAppDao.insertMovies(movies)

    fun insertAllTvShow(tvShows: List<TvShowEntity>) = mAppDao.insertTvshows(tvShows)

    fun insertDsvMovies(dsvMovie:List<DiscoveryMovieEntity>) = mAppDao.insertDsvMovies(dsvMovie)

    fun insertDsvTv(dsvTv:List<DiscoveryTvEntity>) = mAppDao.insertDsvTvshows(dsvTv)

    fun insertTrend(trend:List<TrendEntity>) = mAppDao.insertTrend(trend)

    fun insertMovies(movie: MovieEntity) = mAppDao.insertDetailMovies(movie)

    fun insertTvShow(tvShow: TvShowEntity) = mAppDao.insertDetailTvshows(tvShow)

    fun setFavoriteMovie(movie: MovieEntity, newState:Boolean){
        movie.favorite = newState
        mAppDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity, newState: Boolean){
        tvShow.favorite = newState
        mAppDao.updateTvShow(tvShow)
    }

}