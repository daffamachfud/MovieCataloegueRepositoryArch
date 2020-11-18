package com.onoh.moviecataloeguerepositoryarch.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.*
import com.onoh.moviecataloeguerepositoryarch.vo.Resource


interface AppDataSource {
    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(movieId:Int):LiveData<Resource<MovieEntity>>

    fun getAllTvshows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailTvshow(tvShowId:Int): LiveData<Resource<TvShowEntity>>

    fun getDsvMovies():LiveData<Resource<PagedList<DiscoveryMovieEntity>>>

    fun getDsvTv() : LiveData<Resource<PagedList<DiscoveryTvEntity>>>

    fun getTrend() : LiveData<Resource<PagedList<TrendEntity>>>

    fun getCastMovie(movieId: Int) : LiveData<List<CastMovieEntity>>

    fun getCastTv(tvShowId: Int) : LiveData<List<CastTvEntity>>

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>

    fun getFavoriteTv():LiveData<PagedList<TvShowEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state:Boolean)

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)
}