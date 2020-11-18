package com.onoh.moviecataloeguerepositoryarch.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.onoh.moviecataloeguerepositoryarch.data.local.LocalDataSource
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.*
import com.onoh.moviecataloeguerepositoryarch.data.remote.ApiResponse
import com.onoh.moviecataloeguerepositoryarch.data.remote.RemoteDataSource
import com.onoh.moviecataloeguerepositoryarch.data.remote.response.*
import com.onoh.moviecataloeguerepositoryarch.utils.AppExecutors
import com.onoh.moviecataloeguerepositoryarch.vo.Resource


class AppRepository private constructor(private val remoteDataSource: RemoteDataSource,
                                        private val localDataSource: LocalDataSource, private val appExecutors: AppExecutors
) :
    AppDataSource {

    companion object{
        @Volatile
        private var instance: AppRepository?=null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :NetworkBoundResource<PagedList<MovieEntity>,List<MoviesResult?>?>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4).build()
                return LivePagedListBuilder(localDataSource.getAllMovies(),config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MoviesResult?>?>> = remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<MoviesResult?>?) {
                val movieList = ArrayList<MovieEntity>()
                if (data != null) {
                    for (response in data) {
                        val movieResult = MovieEntity(response?.id,response?.title,response?.voteAverage,response?.overview,response?.releaseDate,response?.posterPath,response?.backdropPath,false)
                        movieList.add(movieResult)
                    }
                }
                localDataSource.insertAllMovies(movieList)
            }
        }.asLiveData()
    }


    override fun getDetailMovie(movieId: Int): LiveData<Resource<MovieEntity>> {

        return object : NetworkBoundResource<MovieEntity, DetailMvResponse?>(appExecutors){
            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getLocalDetailMovie(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<DetailMvResponse?>> = remoteDataSource.getDetailMovie(movieId)

            override fun saveCallResult(data: DetailMvResponse?) {
               val movieResult = MovieEntity(data?.id,data?.title,data?.voteAverage,data?.overview,data?.releaseDate,data?.posterPath,data?.backdropPath,false)
                localDataSource.insertMovies(movieResult)
            }
        }.asLiveData()
    }

    override fun getAllTvshows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :NetworkBoundResource<PagedList<TvShowEntity>,List<TvShowsResult?>?>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(4)
                    .setPageSize(4).build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(),config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowsResult?>?>> = remoteDataSource.getAllTvShow()

            override fun saveCallResult(data: List<TvShowsResult?>?) {
                val tvShowList = ArrayList<TvShowEntity>()
                if (data != null) {
                    for (response in data) {
                        val tvShow = TvShowEntity(response?.id,response?.originalName,response?.voteAverage,response?.overview,0,0,response?.posterPath,response?.backdropPath,false)
                        tvShowList.add(tvShow)
                    }
                }
                localDataSource.insertAllTvShow(tvShowList)
            }
        }.asLiveData()
    }


    override fun getDetailTvshow(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, DetailTvShowResponse?>(appExecutors){
            override fun loadFromDB(): LiveData<TvShowEntity> = localDataSource.getLocalDetailTvshow(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<DetailTvShowResponse?>> = remoteDataSource.getDetailTvShow(tvShowId)

            override fun saveCallResult(data: DetailTvShowResponse?) {
                val tvResult = TvShowEntity(data?.id,data?.name,data?.voteAverage,data?.overview,data?.numberOfEpisodes,data?.numberOfSeasons,data?.posterPath,data?.backdropPath,false)
                Log.d("onResponseDetailTv",data?.overview.toString())
                localDataSource.insertTvShow(tvResult)
            }
        }.asLiveData()
    }

    override fun getDsvMovies(): LiveData<Resource<PagedList<DiscoveryMovieEntity>>> {
        return object : NetworkBoundResource<PagedList<DiscoveryMovieEntity>, List<MoviesResult?>?>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<DiscoveryMovieEntity>> {
                val config = PagedList.Config.Builder().setEnablePlaceholders(false).setInitialLoadSizeHint(4)
                    .setPageSize(4).build()
                return LivePagedListBuilder(localDataSource.getDsvMovies(),config).build()
            }

            override fun shouldFetch(data: PagedList<DiscoveryMovieEntity>?): Boolean = data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MoviesResult?>?>> = remoteDataSource.getDiscoveryMovie()

            override fun saveCallResult(data: List<MoviesResult?>?) {
                val movieList = ArrayList<DiscoveryMovieEntity>()
                if (data != null) {
                    for (response in data) {
                        val movie = DiscoveryMovieEntity(response?.id,response?.title,response?.voteAverage,response?.overview,response?.releaseDate,response?.backdropPath,false)
                        movieList.add(movie)
                    }
                }
                localDataSource.insertDsvMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getDsvTv(): LiveData<Resource<PagedList<DiscoveryTvEntity>>> {
        return object : NetworkBoundResource<PagedList<DiscoveryTvEntity>, List<TvShowsResult?>?>(appExecutors) {

            override fun loadFromDB(): LiveData<PagedList<DiscoveryTvEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4).build()
                return LivePagedListBuilder(localDataSource.getDsvTv(),config).build()
            }

            override fun shouldFetch(data: PagedList<DiscoveryTvEntity>?): Boolean = data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvShowsResult?>?>> = remoteDataSource.getDiscoveryTv()

            override fun saveCallResult(data: List<TvShowsResult?>?) {
                val tvShowList = ArrayList<DiscoveryTvEntity>()
                if (data != null) {
                    for (response in data) {
                        val tvShow = DiscoveryTvEntity(response?.id,response?.originalName,response?.voteAverage,response?.overview,response?.firstAirDate,response?.posterPath,false)
                        tvShowList.add(tvShow)
                    }
                }
                localDataSource.insertDsvTv(tvShowList)
            }
        }.asLiveData()
    }

    override fun getTrend(): LiveData<Resource<PagedList<TrendEntity>>> {
        return object : NetworkBoundResource<PagedList<TrendEntity>, List<TrendResult?>?>(appExecutors) {

            override fun loadFromDB(): LiveData<PagedList<TrendEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4).build()
                return LivePagedListBuilder(localDataSource.getTrend(),config).build()
            }

            override fun shouldFetch(data: PagedList<TrendEntity>?): Boolean = data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TrendResult?>?>> = remoteDataSource.getTrend()

            override fun saveCallResult(data: List<TrendResult?>?) {
                val trendList = ArrayList<TrendEntity>()
                if (data != null) {
                    for (response in data) {
                        val trend = TrendEntity(response?.id,response?.originalTitle,response?.originalName,response?.voteAverage,response?.overview,response?.firstAirDate,response?.backdropPath,response?.mediaType,false)
                        trendList.add(trend)
                    }
                }
                localDataSource.insertTrend(trendList)
            }
        }.asLiveData()
    }

    override fun getCastMovie(movieId: Int): LiveData<List<CastMovieEntity>> {
       val castResults  = MutableLiveData<List<CastMovieEntity>>()
        remoteDataSource.getMovieCast(movieId,object:RemoteDataSource.LoadCastCallback{
            override fun onAllCastReceived(castResult: List<CastResult?>?) {
                val castList = ArrayList<CastMovieEntity>()
                if(castResult != null){
                    for(response in castResult){
                        val cast = CastMovieEntity(response?.castId,response?.character,response?.gender,response?.creditId,response?.name,response?.profilePath,response?.castId,response?.order)
                        castList.add(cast)
                    }
                    castResults.postValue(castList)
                }
            }
        })
        return castResults
    }

    override fun getCastTv(tvShowId: Int): LiveData<List<CastTvEntity>> {
        val castResults  = MutableLiveData<List<CastTvEntity>>()
        remoteDataSource.getTvCast(tvShowId,object:RemoteDataSource.LoadCastCallback{
            override fun onAllCastReceived(castResult: List<CastResult?>?) {
                val castList = ArrayList<CastTvEntity>()
                if(castResult != null){
                    for(response in castResult){
                        val cast = CastTvEntity(response?.castId,response?.character,response?.gender,response?.creditId,response?.name,response?.profilePath,response?.castId,response?.order)
                        castList.add(cast)
                    }
                    castResults.postValue(castList)
                }
            }
        })
        return castResults
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTv(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvshows(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        val runnable = { localDataSource.setFavoriteMovie(movie, state) }
        appExecutors.diskIO().execute(runnable)
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean){
        val runnable = { localDataSource.setFavoriteTvShow(tvShow, state) }
        appExecutors.diskIO().execute(runnable)
    }
}