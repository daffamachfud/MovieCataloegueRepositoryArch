package com.onoh.moviecataloeguerepositoryarch.data.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.*


@Dao
interface AppDao {

    @Query("SELECT * FROM moviesentities")
    fun getAllMovies(): DataSource.Factory<Int,MovieEntity>

    @Query("SELECT * FROM tvshowentities")
    fun getAllTvShows():DataSource.Factory<Int,TvShowEntity>

    @Query("SELECT * FROM dsvmoviesentities")
    fun getDsvMovies(): DataSource.Factory<Int,DiscoveryMovieEntity>

    @Query("SELECT * FROM dsvtvsentities")
    fun getDsvTv(): DataSource.Factory<Int,DiscoveryTvEntity>

    @Query("SELECT * FROM trendentities")
    fun getTrend():DataSource.Factory<Int,TrendEntity>

    @Query("SELECT * FROM moviesentities where favorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int,MovieEntity>

    @Query("SELECT * FROM tvshowentities where favorite = 1")
    fun getFavoriteTvshows():DataSource.Factory<Int,TvShowEntity>

    @Query("SELECT * FROM moviesentities WHERE movieId = :movieId")
    fun getDetailMovie(movieId:Int):LiveData<MovieEntity>

    @Query("SELECT * FROM tvshowentities WHERE tvShowId = :tvShowId")
    fun getDetailTvShow(tvShowId:Int):LiveData<TvShowEntity>

    @Update
    fun updateMovie(movieEntity: MovieEntity)

    @Update
    fun updateTvShow(tvShowEntity: TvShowEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvshows(tvshows: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDsvMovies(dsvMovies: List<DiscoveryMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrend(trend: List<TrendEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDsvTvshows(dsvTvshows: List<DiscoveryTvEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailMovies(movies: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailTvshows(tvshows: TvShowEntity)


}