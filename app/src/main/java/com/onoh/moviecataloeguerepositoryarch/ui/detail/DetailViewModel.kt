package com.onoh.moviecataloeguerepositoryarch.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.MovieEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.TvShowEntity
import com.onoh.moviecataloeguerepositoryarch.vo.Resource
import com.onoh.moviecataloeguerepositoryarch.data.AppRepository
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.CastMovieEntity
import com.onoh.moviecataloeguerepositoryarch.data.local.entity.CastTvEntity


class DetailViewModel(private val appRepository: AppRepository) :ViewModel(){
  val movieId = MutableLiveData<Int>()
    val tvshowId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId :Int){
        this.movieId.value = movieId
    }
    fun setSelectedtvShow(tvshowId :Int){
        this.tvshowId.value= tvshowId
    }

    var detailMovie:LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId){
        appRepository.getDetailMovie(it)
    }

    var detailTvShow:LiveData<Resource<TvShowEntity>> = Transformations.switchMap(tvshowId){
        appRepository.getDetailTvshow(it)
    }


    fun getCastMv():LiveData<List<CastMovieEntity>> = Transformations.switchMap(movieId){
        appRepository.getCastMovie(it)
    }
    fun getCastTv():LiveData<List<CastTvEntity>> = Transformations.switchMap(tvshowId){
        appRepository.getCastTv(it)
    }

    fun setMovieFavorite(){
        val movie = detailMovie.value
        if(movie != null){
            val movieEntity :MovieEntity = movie.data!!
            val newState = !movieEntity.favorite
            appRepository.setFavoriteMovie(movieEntity, newState)
        }
    }

    fun setTvFavorite(){
        val tv = detailTvShow.value
        if(tv != null){
            val tvEntity :TvShowEntity = tv.data!!
            val newState = !tvEntity.favorite
            appRepository.setFavoriteTvShow(tvEntity, newState)
        }
    }
}